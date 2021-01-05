package com.aqiindia.datasource.dto;

import com.google.cloud.storage.BlobInfo;
import feign.Response;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileObject {

    private BlobInfo blobInfo;
    private Response.Body fileContent;

}
