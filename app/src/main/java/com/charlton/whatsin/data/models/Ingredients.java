package com.charlton.whatsin.data.models;

import java.io.Serializable;

/**
 * Created by cj on 11/4/16.
 */

public class Ingredients implements Serializable
{
    private String id;

    private String rank;

    private String text;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getRank ()
    {
        return rank;
    }

    public void setRank (String rank)
    {
        this.rank = rank;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", rank = "+rank+", text = "+text+"]";
    }
}
