package com.aqiindia.datasource.gateway;

import com.aqiindia.datasource.util.Constants;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ApiDataGov", url = "${aqi.dataingest.data-source.url.base}")
public interface ApiDataGovFeignClient {

    /**
     * Method to fetch AQI data from data.gov.in
     * The data is refreshed every hour.
     * The response String could be converted to required file / json format
     * @param apiKey
     * @param format
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("${aqi.dataingest.data-source.url.service}")
    Response fetchAQIData(
            @RequestParam(Constants.CONST_AQI_SOURCE_URL_QUERY_PARAM_APIKEY) String apiKey,
            @RequestParam(Constants.CONST_AQI_SOURCE_URL_QUERY_PARAM_FORMAT) String format,
            @RequestParam(Constants.CONST_AQI_SOURCE_URL_QUERY_PARAM_OFFSET) long offset,
            @RequestParam(Constants.CONST_AQI_SOURCE_URL_QUERY_PARAM_LIMIT) long limit
    );

}
