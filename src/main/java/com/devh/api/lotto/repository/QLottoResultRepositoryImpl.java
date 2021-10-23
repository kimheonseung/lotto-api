package com.devh.api.lotto.repository;

import static com.devh.api.lotto.entity.QLottoResult.lottoResult;

import java.util.List;

import com.devh.api.lotto.entity.LottoResult;
import com.devh.api.lotto.entity.QLottoResult;
import com.devh.api.lotto.exception.QueryException;
import com.devh.common.api.search.vo.SearchConditionVO;
import com.devh.common.api.search.vo.SearchParameterVO;
import com.devh.common.util.ExceptionUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * Description :
 *     LottoResult 관련 QueryDSL을 사용하는 Repository
 * ===============================================
 * Member fields :
 *     JPAQueryFactory jpaQueryFactory
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2021-10-21
 * </pre>
 */
@Log4j2
public class QLottoResultRepositoryImpl extends QuerydslRepositorySupport implements QLottoResultRepository  {
    
    private final JPAQueryFactory jpaQueryFactory;    

    public QLottoResultRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(LottoResult.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

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
    @SuppressWarnings("rawtypes")
    @Override
    public List<LottoResult> findBySearchParameter(SearchParameterVO searchParameterVO) throws QueryException {
        
        List<LottoResult> result = null;

        try {
            
            List<SearchConditionVO> conditionList = searchParameterVO.getSearchConditionVOList();
            QLottoResult qLottoResult = QLottoResult.lottoResult;

            /* 조건문 설정 */
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            conditionList.forEach(condition -> {
                parseCondition(condition, qLottoResult, booleanBuilder);
            });

            /* 페이징 설정 */
            final int offset       = searchParameterVO.getOffset();
            final int limit        = searchParameterVO.getRows();
            OrderSpecifier order   = parseSort(searchParameterVO, qLottoResult);
            
            /* Query 파싱 성공 여부 */
            if(booleanBuilder.hasValue()) {
                result = jpaQueryFactory
                    .selectFrom(lottoResult)
                    .where(booleanBuilder)
                    .offset(offset)
                    .limit(limit)
                    .orderBy(order)
                    .fetch();
            } else {
                result = jpaQueryFactory
                    .selectFrom(lottoResult)
                    .offset(offset)
                    .limit(limit)
                    .orderBy(order)
                    .fetch();
            }
            
        } catch (Exception e) {
            log.error(ExceptionUtils.getInstance().getPrintStackTraceToString(e));
            throw new QueryException(e.getMessage());
        }

        return result;
    }
    
}
