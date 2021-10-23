package com.devh.api.lotto.repository;

import java.util.List;

import com.devh.api.lotto.entity.LottoResult;
import com.devh.api.lotto.projection.LottoResultNumberArrayProjection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * Description :
 *     LottoResult 엔티티와 대응되는 쿼리 수행 인터페이스
 *     QLottoResultRepository 다중 상속
 * ===============================================
 * Member fields :
 *
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2021-02-28
 * </pre>
 */
public interface LottoResultRepository extends JpaRepository<LottoResult, Integer>, QLottoResultRepository {

    /**
     * <pre>
     * Description
     *     DB에 저장된 최신 회차를 조회
     * ===============================================
     * Parameters :
     *
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
    LottoResult findFirstByOrderByTurnDesc();

    List<LottoResultNumberArrayProjection> findAllBy();
}
