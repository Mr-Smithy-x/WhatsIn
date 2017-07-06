package com.charlton.whatsin.data.models;

/**
 * Created by cj on 11/5/16.
 */
public class Response<T> {
    int status;
    String message;
    String explain;
    T data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getExplain() {
        return explain;
    }

    public T getData() {
        return data;
    }
}
