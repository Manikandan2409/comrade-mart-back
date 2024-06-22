package com.demon.comrade_mart.utils;

public class AccessDeniedException extends  RuntimeException{
    public  AccessDeniedException(String message, Exception e){
        super(message);
    }
}
