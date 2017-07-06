package com.charlton.whatsin.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cj on 11/4/16.
 */

public class Languages_codes implements Serializable {
    @SerializedName("en:english")
    private String en;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @Override
    public String toString() {
        return "ClassPojo [en = " + en + "]";
    }
}
