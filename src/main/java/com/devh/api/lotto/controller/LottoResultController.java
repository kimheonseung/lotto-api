package com.devh.api.lotto.controller;

import java.util.List;

import com.devh.api.lotto.dto.LottoResultVO;
import com.devh.api.lotto.service.LottoResultService;
import com.devh.common.api.constant.ApiStatus;
import com.devh.common.api.response.ApiResponse;
import com.devh.common.api.search.vo.PagingVO;
import com.devh.common.api.search.vo.SearchParameterVO;
import com.devh.common.util.ExceptionUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("api/v1/lotto-reuslt")
@RequiredArgsConstructor
@Log4j2
public class LottoResultController {
    
    private final LottoResultService lottoResultService;

    @GetMapping("latest")
    public ApiResponse<LottoResultVO> getLatestResponse() {

        ApiResponse<LottoResultVO> result;

        try {
            result = ApiResponse.success(ApiStatus.Success.OK, lottoResultService.getAll());
        } catch (Exception e) {
            final String stacktrace = ExceptionUtils.getInstance().getPrintStackTraceToString(e);
            log.error(stacktrace);
            result = ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, stacktrace);
        }

        return result;
    }

    @GetMapping("search")
    public ApiResponse<LottoResultVO> getSearchResponse(SearchParameterVO searchParameterVO) {
        
        ApiResponse<LottoResultVO> result;

        try {
            List<LottoResultVO> resultList = lottoResultService.getSearch(searchParameterVO);
            result = ApiResponse.success(ApiStatus.Success.OK, resultList, PagingVO.build(searchParameterVO, resultList.size()));
        } catch (Exception e) {
            final String stacktrace = ExceptionUtils.getInstance().getPrintStackTraceToString(e);
            log.error(stacktrace);
            result = ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, stacktrace);
        }

        return result;
    }
    
}
