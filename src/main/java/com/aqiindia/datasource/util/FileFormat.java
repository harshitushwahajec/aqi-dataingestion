package com.aqiindia.datasource.util;

public enum FileFormat {
    CSV("csv"),
    XML("xml"),
    JSON("json");

    String value;

    FileFormat(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
