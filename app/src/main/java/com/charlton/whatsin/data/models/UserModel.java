package com.charlton.whatsin.data.models;

import java.io.Serializable;

/**
 * Created by cj on 11/5/16.
 */
public class UserModel implements Serializable{
    int user_id;
    String user_name;
    String user_date;
    String user_profile_avatar;
    String user_profile_cover;
    String user_email;
    String user_fb_id;
    String user_fcm_id;

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_date() {
        return user_date;
    }

    public String getUser_profile_avatar() {
        return user_profile_avatar;
    }

    public String getUser_profile_cover() {
        return user_profile_cover;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_fb_id() {
        return user_fb_id;
    }

    public String getUser_fcm_id() {
        return user_fcm_id;
    }
}
