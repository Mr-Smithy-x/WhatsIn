package com.charlton.whatsin.data.models;

import java.io.Serializable;

/**
 * Created by cj on 11/4/16.
 */


public class UPC implements Serializable{

    private Product product;

    private String status_verbose;

    private int status;

    private String code;

    public Product getProduct() {
        return product;
    }

    public String getStatus_verbose() {
        return status_verbose;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ClassPojo [product = " + product + ", status_verbose = " + status_verbose + ", status = " + status + ", code = " + code + "]";
    }
}

