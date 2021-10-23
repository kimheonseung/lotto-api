package com.devh.api.lotto.controller;

import com.devh.api.lotto.service.LottoResultService;
import com.devh.api.lotto.service.LottoResultStoreService;
import com.devh.common.api.response.ResultToJsonConverter;
import com.devh.common.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lotto")
@RequiredArgsConstructor
public class LottoController {
    private final Logger logger = LoggerFactory.getLogger(LottoController.class);

    /* DI */
    private final LottoResultStoreService lottoResultStoreService;
    private final LottoResultService lottoResultService;

    @GetMapping("number-count-list")
    public ResponseEntity<Object> getAllNumberList() {
        logger.info("[GET] /micro-lotto/number-count-list");
        ResponseEntity<Object> result;
        ResultToJsonConverter resultToJsonConverter = ResultToJsonConverter.init();
        try {
            resultToJsonConverter.putResultMapToResultJson(lottoResultService.getLottoNumberCountDTO());
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.OK);
        } catch (Exception e) {
            ExceptionUtils.getInstance().printErrorLogWithException(logger, e);
            resultToJsonConverter.putResultMapToExceptionInformation(e);
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @PostMapping("aggregation/store-address")
    public ResponseEntity<Object> postAggregationStoreAddress() {
        logger.info("[GET] /micro-lotto/aggregation/store-address");
        ResponseEntity<Object> result;
        ResultToJsonConverter resultToJsonConverter = ResultToJsonConverter.init();
        try {
            resultToJsonConverter.putResultMapToResultJson(lottoResultStoreService.getTotalAggregationStoreAddressMap());
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.OK);
        } catch (Exception e) {
            ExceptionUtils.getInstance().printErrorLogWithException(logger, e);
            resultToJsonConverter.putResultMapToExceptionInformation(e);
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;

    }

    @GetMapping("store-information/address1")
    public ResponseEntity<Object> getStoreInformationAboutAddress1() {
        logger.info("[GET] /micro-lotto/store-information/address1");
        ResponseEntity<Object> result;
        ResultToJsonConverter resultToJsonConverter = ResultToJsonConverter.init();
        try {
            resultToJsonConverter.putResultMapToResultJson(lottoResultStoreService.getAddress1StoreCountMapList());
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.OK);
        } catch (Exception e) {
            ExceptionUtils.getInstance().printErrorLogWithException(logger, e);
            resultToJsonConverter.putResultMapToExceptionInformation(e);
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @GetMapping("store-information/address2/{address1}")
    public ResponseEntity<Object> getStoreInformationAboutAddress2(@PathVariable("address1") String address1) {
        logger.info("[GET] /micro-lotto/store-information/address2/"+address1);
        ResponseEntity<Object> result;
        ResultToJsonConverter resultToJsonConverter = ResultToJsonConverter.init();
        try {
            resultToJsonConverter.putResultMapToResultJson(lottoResultStoreService.getAddress2StoreCountMapList(address1));
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.OK);
        } catch (Exception e) {
            ExceptionUtils.getInstance().printErrorLogWithException(logger, e);
            resultToJsonConverter.putResultMapToExceptionInformation(e);
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @GetMapping("store-information/address3/{address1}/{address2}")
    public ResponseEntity<Object> getStoreInformationAboutAddress3(@PathVariable("address1") String address1, @PathVariable("address2") String address2) {
        logger.info("[GET] /micro-lotto/store-information/address3/"+address1+"/"+address2);
        ResponseEntity<Object> result;
        ResultToJsonConverter resultToJsonConverter = ResultToJsonConverter.init();
        try {
            resultToJsonConverter.putResultMapToResultJson(lottoResultStoreService.getAddress3StoreDTOList(address1, address2));
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.OK);
        } catch (Exception e) {
            ExceptionUtils.getInstance().printErrorLogWithException(logger, e);
            resultToJsonConverter.putResultMapToExceptionInformation(e);
            result = new ResponseEntity<>(resultToJsonConverter.get(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}