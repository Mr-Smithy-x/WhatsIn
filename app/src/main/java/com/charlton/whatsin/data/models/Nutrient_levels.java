package com.charlton.whatsin.data.models;

import java.io.Serializable;

/**
 * Created by cj on 11/4/16.
 */

public class Nutrient_levels implements Serializable
{
    private String fat;

    private String sugars;

    private String salt;

    public String getFat() {
        return fat;
    }

    public String getSugars() {
        return sugars;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [fat = "+fat+", sugars = "+sugars+", salt = "+salt+"]";
    }
}