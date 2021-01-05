package com.aqiindia.datasource.controller;

import com.aqiindia.datasource.service.AqiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("notvalidanymore")
public class AqiDataIngestionController {

    @Autowired
    AqiDataService aqiDataService;

    @GetMapping("/start")
    public void startAQIDataIngestion (){
        Runnable dataLoadThread = () -> {
            try {
                aqiDataService.startDataTransferProcess();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(dataLoadThread).start();
    }

}
