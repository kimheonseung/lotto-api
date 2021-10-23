package com.devh.api.lotto.repository;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.devh.api.lotto.entity.LottoResult;
import com.devh.api.lotto.entity.QLottoResult;
import com.devh.api.lotto.exception.QueryException;
import com.devh.common.api.search.constant.SearchOperator;
import com.devh.common.api.search.vo.SearchConditionVO;
import com.devh.common.api.search.vo.SearchParameterVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Description :
 *     QueryDSL용 인터페이스
 *     일반 JPA Repository에 통합하여 사용한다.
 *     LottoResultRepository에 JPARepository와 다중상속 
 *     (LottoResultRepository만을 주입받아 JPARepository와 이 인터페이스의 메소드를 모두 사용)
 *     QClass가 class not found exception 발생인 경우
 *         프로젝트 경로의 .classpath에서 scope를 main으로 변경
 *         <classpathentry kind="src" output="bin/querydsl" path="build/generated/querydsl">
 *                <!-- scope to main (vscode class not found) -->
 *                <attributes>
 *                        <!-- <attribute name="gradle_scope"  value="querydsl"/> -->
 *                        <!-- <attribute name="gradle_used_by_scope" value="querydsl"/> -->
 *                        <attribute name="gradle_scope" value="main"/>
 *                        <attribute name="gradle_used_by_scope" value="main"/>
 *                </attributes>
 *        </classpathentry>
 * ===============================================
 * Member fields :
 *     
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2021-10-23
 * </pre>
 */
public interface QLottoResultRepository {

    final Logger log = LoggerFactory.getLogger(QLottoResultRepository.class);

    /**
     * <pre>
     * Description :
     *     검색 조건에 맞는 결과 쿼리
     *     조건을 파싱하여 QueryDSL 실행
     * ===============================================
     * Parameters :
     *     SearchParameterVO searchParameterVO
     * Returns :
     *     List<LottoResult>
     * Throws :
     *     QueryException
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-10-23
     * </pre>
     */
    List<LottoResult> findBySearchParameter(SearchParameterVO searchParameterVO) throws QueryException;


    /**
     * <pre>
     * Description :
     *     조건 파싱
     *     reflection을 이용해 넘겨받은 조건의 컬럼과 연산을 파싱
     *     완성된 조건은 인자로 받은 BooleanBuilder에 기록
     *     파싱에 실패한 경우 해당 조건은 조건에 기록되지 않음
     * ===============================================
     * Parameters :
     *     SearchConditionVO searchConditionVO
     *     QLottoResult qLottoResult
     *     BooleanBuilder booleanBuilder
     * Returns :
     *     
     * Throws :
     *     
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-10-23
     * </pre>
     */
    default void parseCondition(SearchConditionVO searchConditionVO, QLottoResult qLottoResult, BooleanBuilder booleanBuilder) {

        final String tempKey = searchConditionVO.getSearchKey();
        final SearchOperator tempOp = searchConditionVO.getSearchOperator();
        final Object tempVal = searchConditionVO.getSearchValue();

        BooleanExpression exp = null;
        try {

            /* 넘겨받은 key에 해당하는 컬럼 관련 field */
            Field field = QLottoResult.class.getDeclaredField(tempKey);
            Object f = field.get(qLottoResult);

            /* 해당 field가 갖고있는 메소드 중 넘겨받은 연산자와 일치하는 메소드 찾기 */
            for(Method m : f.getClass().getMethods()) {
                if(m.getName().equalsIgnoreCase(tempOp.getDslName())) {
                    try {
                        exp = (BooleanExpression) m.invoke(f, tempVal);
                        break;
                    } catch (IllegalArgumentException ignored) { 
                        /* Method Parameter type mismatches. */ 
                        log.warn(String.format("Type mismatches: requested( %s %s %s )", tempKey, tempOp, tempVal.toString()));
                    }
                }
            }
                
            log.info(exp.toString());
            booleanBuilder.and(exp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * <pre>
     * Description :
     *     검색 조건에서 정렬 정보를 파싱
     * ===============================================
     * Parameters :
     *     SearchParameterVO searchParameterVO 
     *     QLottoResult qLottoResult
     * Returns :
     *     OrderSpecifier
     * Throws :
     *     
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-10-23
     * </pre>
     */
    @SuppressWarnings("rawtypes")
    default OrderSpecifier parseSort(SearchParameterVO searchParameterVO, QLottoResult qLottoResult) {
        OrderSpecifier orderSpecifier = qLottoResult.turn.desc();

        final String sortOrder = searchParameterVO.getSortOrder().toString();
        String sortIndex = searchParameterVO.getSortIndex();

        if(sortIndex == null)
            sortIndex = "turn";

        try {
            Field field = QLottoResult.class.getDeclaredField(sortIndex);
            Object f = field.get(qLottoResult);

            for(Method m : f.getClass().getMethods()) {
                if(m.getName().equalsIgnoreCase(sortOrder)) {
                    try {
                        orderSpecifier = (OrderSpecifier) m.invoke(f);
                        break;
                    } catch (IllegalArgumentException ignored) { 
                        /* Method Parameter type mismatches. */ 
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderSpecifier;
    }
}
