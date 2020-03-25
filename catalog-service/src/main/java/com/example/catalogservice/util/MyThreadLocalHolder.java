package com.example.catalogservice.util;

public class MyThreadLocalHolder {
    private static final ThreadLocal<String> COORELATION_ID = new ThreadLocal();

    public static void setCorrelationId(String correlationId){
        COORELATION_ID.set(correlationId);
    }

    public static String getCorrelationId(){
        return  COORELATION_ID.get();
    }
}
