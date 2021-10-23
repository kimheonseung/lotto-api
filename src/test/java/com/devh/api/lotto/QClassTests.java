package com.devh.api.lotto;

import java.util.ArrayList;
import java.util.List;

import com.devh.api.lotto.constant.LottoConstant;
import com.devh.api.lotto.entity.LottoResult;
import com.devh.api.lotto.exception.QueryException;
import com.devh.api.lotto.repository.LottoResultRepository;
import com.devh.api.lotto.repository.QLottoResultRepository;
import com.devh.api.lotto.repository.QLottoResultRepositoryImpl;
import com.devh.common.api.search.constant.SearchDataType;
import com.devh.common.api.search.constant.SearchOperator;
import com.devh.common.api.search.vo.SearchConditionVO;
import com.devh.common.api.search.vo.SearchParameterVO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QClassTests {

    @Autowired
    private LottoResultRepository lottoResultRepository;

    @Test
    public void dynamicQueryTest() {
        System.out.println("========================= Dynamic Query Test Start =========================\n\n");
        
        SearchParameterVO searchParameterVO = new SearchParameterVO();
        List<SearchConditionVO> conditionList = createConditionList();
        searchParameterVO.setSearchConditionVOList(conditionList);

        List<LottoResult> queryResult;
        try {
            queryResult = lottoResultRepository.findBySearchParameter(searchParameterVO);
            queryResult.forEach(System.out::println);
        } catch (QueryException e) {
            e.printStackTrace();
        }
    }

    private List<SearchConditionVO> createConditionList() {
        List<SearchConditionVO> conditionList = new ArrayList<>();

        conditionList.add(SearchConditionVO.builder()
            .searchKey(LottoConstant.LottoResultKey.TURN.getCamelCase())
            .searchValue(366)
            .searchDataType(SearchDataType.INTEGER)
            .searchOperator(SearchOperator.EQUAL)
            .build()
        );

        conditionList.add(SearchConditionVO.builder()
            .searchKey(LottoConstant.LottoResultKey.NUMBER1.getCamelCase())
            .searchValue("6")
            .searchDataType(SearchDataType.STRING)
            .searchOperator(SearchOperator.NOT_EQUAL)
            .build()
        );

        return conditionList;
    }
    
}
