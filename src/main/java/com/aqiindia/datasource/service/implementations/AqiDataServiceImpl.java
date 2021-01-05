package com.aqiindia.datasource.service.implementations;

import com.aqiindia.datasource.dto.FileObject;
import com.aqiindia.datasource.gateway.ApiDataGovFeignClient;
import com.aqiindia.datasource.service.AqiDataService;
import com.aqiindia.datasource.util.Constants;
import com.aqiindia.datasource.util.FileFormat;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;

@Service
public class AqiDataServiceImpl implements AqiDataService {

    @Autowired
    ApiDataGovFeignClient dataGovFeignClient;

    @Autowired
    Storage cloudStorage;

    @Override
    public void startDataTransferProcess() throws IOException {

        final long pageOffset = 0l;

        // Fetch the latest AQI data in a file
        FileObject fileData = fetchDataFromSource(
                Constants.ENV_DATA_GOV_IN_APIKEY,
                Constants.ENV_DATA_GOV_IN_FILE_FORMAT,
                pageOffset,
                Constants.ENV_DATA_GOV_IN_PAGE_LIMIT
        );

        // Transfer the file to Cloud Storage bucket
        storeDataAtDestination(fileData);

    }

    private FileObject fetchDataFromSource(
            String apiKey, FileFormat fileFormat, long offset, long limit
    ) {
        Response response = dataGovFeignClient.fetchAQIData(apiKey, fileFormat.getValue(), offset, limit);

        return FileObject.builder()
                .blobInfo(
                        BlobInfo.newBuilder(Constants.ENV_STORAGE_BUCKET, generateTargetFileName())
                                .setContentDisposition(((List<String>)response.headers().get(Constants.HEADER_CONTENT_DISPOSITION)).get(0))
                                .setContentType(((List<String>)response.headers().get(Constants.HEADER_CONTENT_TYPE)).get(0))
                                .build()
                )
                .fileContent(response.body())
                .build();

    }

    private void storeDataAtDestination (FileObject fileData) throws IOException {
        boolean result = false;

        WriteChannel writer = cloudStorage.writer(fileData.getBlobInfo());
        InputStream inputStream = fileData.getFileContent().asInputStream();

        byte[] buffer = new byte[10_240];
        int limit;

        while((limit = inputStream.read(buffer)) >= 0 ){
            writer.write(ByteBuffer.wrap(buffer, 0, limit));
        }
        writer.close();
    }

    private String generateTargetFileName(){
        return String.format(Constants.ENV_STORAGE_FILENAME_PATTERN, new Date().toString());
    }

}
