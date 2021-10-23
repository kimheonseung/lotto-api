package com.devh.api.lotto.service;

import com.devh.api.lotto.dto.LottoNumberCountDTO;
import com.devh.api.lotto.dto.LottoResultAllNumberDTO;
import com.devh.api.lotto.dto.LottoResultVO;
import com.devh.api.lotto.entity.LottoResult;
import com.devh.api.lotto.exception.QueryException;
import com.devh.api.lotto.projection.LottoResultNumberArrayProjection;
import com.devh.api.lotto.repository.LottoResultRepository;
import com.devh.api.lotto.repository.QLottoResultRepositoryImpl;
import com.devh.common.api.search.vo.SearchParameterVO;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Description :
 *     LottoResultService 구현체
 * ===============================================
 * Member fields :
 *
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2021-02-28
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class LottoResultServiceImpl implements LottoResultService {
   
    /* DI */
    private final LottoResultRepository lottoResultRepository;
    // private final QLottoResultRepository qLottoResultRepository;

    @Override
    public LottoResult getLatest() {
        LottoResult lottoResult = lottoResultRepository.findFirstByOrderByTurnDesc();
        return lottoResult;
    }

    @Override
    public List<LottoResultVO> getSearch(SearchParameterVO searchParameterVO) throws QueryException {
        List<LottoResult> resultList = lottoResultRepository.findBySearchParameter(searchParameterVO);

        List<LottoResultVO> voList = new ArrayList<>();
        resultList.forEach(entity -> voList.add(entityToVo(entity)));

        return voList;
    }

    @Override
    public List<LottoResultVO> getAll() {
        List<LottoResult> list = lottoResultRepository.findAll();

        List<LottoResultVO> dtoList = new ArrayList<>();

        list.forEach(item -> {
            dtoList.add(entityToVo(item));
        });
        return dtoList;
    }


    

    @Override
    public LottoNumberCountDTO getLottoNumberCountDTO() {
        List<LottoResultAllNumberDTO> lottoResultAllNumberDTOList = getLottoResultDTOWithAllNumber();

        Map<Integer, Integer> numberCountMap = new HashMap<>();

        lottoResultAllNumberDTOList.forEach(lottoResultAllNumberDTO -> {
            String allNumber = lottoResultAllNumberDTO.getAllNumber();
            String[] numberArray = allNumber.split(",");

            for(String number : numberArray) {
                int intNumber = Integer.parseInt(number);
                Integer numberCount = numberCountMap.get(intNumber);

                if(numberCount == null)
                    numberCountMap.put(intNumber, 1);
                else
                    numberCountMap.put(intNumber, ++numberCount);

            }

        });

        return LottoNumberCountDTO.builder()
                .numberCountData(numberCountMap)
                .build();
    }

    /**
     * <pre>
     * Description
     *     ,로 연결된 모든 숫자 문자열과 회차정보를 담는 DTO 리스트 반환
     * ===============================================
     * Parameters :
     *
     * Returns :
     *     List<LottoResultAllNumberDTO>
     * Throws :
     *
     * ===============================================
     *
     * Author : HeonSeung Kim
     * Date   : 2021-04-01
     * </pre>
     */
    @Override
    public List<LottoResultAllNumberDTO> getLottoResultDTOWithAllNumber() {
        List<LottoResultNumberArrayProjection> numberArrayProjectionList = lottoResultRepository.findAllBy();

        List<LottoResultAllNumberDTO> result = new ArrayList<>();

        numberArrayProjectionList.forEach(projection -> {
            result.add(
                    LottoResultAllNumberDTO.builder()
                            .turn(Integer.parseInt(projection.getTurn()))
                            .allNumber(projection.getAllNumber())
                            .build()
            );
        });

        return result;
    }
    
}
