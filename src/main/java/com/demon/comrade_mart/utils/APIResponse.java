//package com.demon.ironheart.common;
//
//import org.springframework.http.HttpStatus;
//
//public class APIResponse<T> {
//
//    private Integer status;
//    private T data;
//    private String error;
//
//    public APIResponse(T data, String message) {
//        this.status = HttpStatus.OK.value();
//        this.data = data;
//        this.error = null;
//    }
//
//    public APIResponse(String error, HttpStatus status) {
//        this.status = status.value();
//        this.data = null;
//        this.error = error;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }
//}
package  com.demon.comrade_mart.utils;

import org.springframework.http.HttpStatus;

public class APIResponse {

    private Integer status;
    private Object data;
    private Object error;

    public APIResponse(String data, String message) {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.error = null;
    }

    public APIResponse(String error, HttpStatus status) {
        this.status = status.value();
        this.data = null;
        this.error = error;
    }
    public APIResponse() {
        this.status = HttpStatus.OK.value();
        this.data = null;
        this.error = null;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
