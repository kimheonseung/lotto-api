package com.devh.api.lotto.service;

import com.devh.api.lotto.dto.LottoResultDetailDTO;
import com.devh.api.lotto.entity.LottoResultDetail;
import com.devh.api.lotto.repository.LottoResultDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * Description :
 *     LottoResultDetailService 구현체
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
public class LottoResultDetailServiceImpl implements LottoResultDetailService {
    /* DI */
    private final LottoResultDetailRepository lottoResultDetailRepository;

    @Override
    public List<LottoResultDetailDTO> getDTOListByTurn(Integer turn) {
        List<LottoResultDetail> lottoResultDetailList = lottoResultDetailRepository.getLottoResultDetailListByTurn(turn);
        return entityListToDtoList(lottoResultDetailList);
    }

}
