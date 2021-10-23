package com.devh.api.lotto.repository;

import com.devh.api.lotto.entity.LottoResultStore;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LottoResultStoreRepository extends ElasticsearchRepository<LottoResultStore, String> {
}
