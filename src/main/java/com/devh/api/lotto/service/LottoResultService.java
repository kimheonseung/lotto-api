package com.devh.api.lotto.service;

import com.devh.api.lotto.dto.LottoNumberCountDTO;
import com.devh.api.lotto.dto.LottoResultAllNumberDTO;
import com.devh.api.lotto.dto.LottoResultVO;
import com.devh.api.lotto.entity.LottoResult;
import com.devh.api.lotto.exception.QueryException;
import com.devh.common.api.search.vo.SearchParameterVO;

import java.util.List;

/**
 * <pre>
 * Description : 
 *     LottoResult 엔티티 관련 인터페이스
 * ===============================================
 * Member fields : 
 *     Nothing
 * ===============================================
 * 
 * Author : HeonSeung Kim
 * Date   : 2021-02-28
 * </pre>
 */
public interface LottoResultService {

    LottoResult getLatest();

    List<LottoResultVO> getSearch(SearchParameterVO searchParameterVO) throws QueryException;


    /**
     * <pre>
     * Description
     *     숫자별 나온 횟수를 DTO로 반환하는 메소드
     * ===============================================
     * Parameters :
     *
     * Returns :
     *
     * Throws :
     *
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-04-01
     * </pre>
     */
    LottoNumberCountDTO getLottoNumberCountDTO();

    List<LottoResultAllNumberDTO> getLottoResultDTOWithAllNumber();

    List<LottoResultVO> getAll();

    /**
     * <pre>
     * Description
     *     LottoResultVO -> LottoResult 변환
     * ===============================================
     * Parameters :
     *     LottoResultVO lottoResultVO
     * Returns :
     *     LottoResult
     * Throws :
     *
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-02-28
     * </pre>
     */
    default LottoResult voToEntity(LottoResultVO lottoResultVO) {
        return LottoResult.builder()
                .turn(lottoResultVO.getTurn())
                .date(lottoResultVO.getDate())
                .number1(lottoResultVO.getNumber1())
                .number2(lottoResultVO.getNumber2())
                .number3(lottoResultVO.getNumber3())
                .number4(lottoResultVO.getNumber4())
                .number5(lottoResultVO.getNumber5())
                .number6(lottoResultVO.getNumber6())
                .number7(lottoResultVO.getNumber7())
                .totalSalesPrice(lottoResultVO.getTotalSalesPrice())
                .autoWinnerCount(lottoResultVO.getAutoWinnerCount())
                .semiAutoWinnerCount(lottoResultVO.getSemiAutoWinnerCount())
                .manualWinnerCount(lottoResultVO.getManualWinnerCount())
                .build();
    }

    /**
     * <pre>
     * Description
     *     LottoResult -> LottoResultVO
     * ===============================================
     * Parameters :
     *     LottoResult lottoResult
     * Returns :
     *     LottoResultVO
     * Throws :
     *
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-02-28
     * </pre>
     */
    default LottoResultVO entityToVo(LottoResult lottoResult) {
        return LottoResultVO.builder()
                .turn(lottoResult.getTurn())
                .date(lottoResult.getDate())
                .number1(lottoResult.getNumber1())
                .number2(lottoResult.getNumber2())
                .number3(lottoResult.getNumber3())
                .number4(lottoResult.getNumber4())
                .number5(lottoResult.getNumber5())
                .number6(lottoResult.getNumber6())
                .number7(lottoResult.getNumber7())
                .totalSalesPrice(lottoResult.getTotalSalesPrice())
                .autoWinnerCount(lottoResult.getAutoWinnerCount())
                .semiAutoWinnerCount(lottoResult.getSemiAutoWinnerCount())
                .manualWinnerCount(lottoResult.getManualWinnerCount())
                .build();
    }
}
