package com.aqiindia.datasource.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    private Constants(){
        // The constructor is private so that the class beans cannot be instantiated
        // This class contains only static content
    }

    // AQI data source URL Query Parameter keys
    public static final String CONST_AQI_SOURCE_URL_QUERY_PARAM_APIKEY = "api-key";
    public static final String CONST_AQI_SOURCE_URL_QUERY_PARAM_FORMAT = "format";
    public static final String CONST_AQI_SOURCE_URL_QUERY_PARAM_OFFSET = "offset";
    public static final String CONST_AQI_SOURCE_URL_QUERY_PARAM_LIMIT = "limit";

    // File Header field keys
    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    // Environment variables fields to be used across the application
    public static String ENV_DATA_GOV_IN_APIKEY;
    public static FileFormat ENV_DATA_GOV_IN_FILE_FORMAT;
    public static long ENV_DATA_GOV_IN_PAGE_LIMIT;
    public static String ENV_STORAGE_BUCKET;
    public static String ENV_STORAGE_FILENAME_PATTERN;

    // Initializing environment variable values from application.properties
    @Value("${aqi.dataingest.data-source.url.api-key}")
    public void setEnvDataGovInApikey(String envDataGovInApikey) {
        ENV_DATA_GOV_IN_APIKEY = envDataGovInApikey;
    }

    @Value("${aqi.dataingest.data-source.url.format}")
    public void setEnvDataGovInFileFormat(FileFormat envDataGovInFileFormat) {
        ENV_DATA_GOV_IN_FILE_FORMAT = envDataGovInFileFormat;
    }

    @Value("${aqi.dataingest.data-source.url.limit}")
    public void setEnvDataGovInPageLimit(long envDataGovInPageLimit) {
        ENV_DATA_GOV_IN_PAGE_LIMIT = envDataGovInPageLimit;
    }

    @Value("${aqi.dataingest.data-destination.storage.bucket}")
    public void setEnvStorageBucket(String envStorageBucket) {
        ENV_STORAGE_BUCKET = envStorageBucket;
    }

    @Value("${aqi.dataingest.data-destination.storage.filenamepattern}")
    public void setEnvStorageFilenamePattern(String envStorageFilenamePattern) {
        ENV_STORAGE_FILENAME_PATTERN = envStorageFilenamePattern;
    }

}
