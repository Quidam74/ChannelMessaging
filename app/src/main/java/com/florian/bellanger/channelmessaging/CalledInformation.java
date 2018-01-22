package com.florian.bellanger.channelmessaging;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bellangf on 22/01/2018.
 */
public class CalledInformation {

    private HashMap<String, String> coupleIDPWD;
    private String url;

    public CalledInformation() {
        coupleIDPWD =  new HashMap<String, String>();
        url  ="none";
    }

    public CalledInformation(HashMap<String, String> coupleIDPWD, String url) {
        this.coupleIDPWD = coupleIDPWD;
        this.url = url;
    }

    public HashMap<String, String> getCoupleIDPWD() {
        return coupleIDPWD;
    }

    public String getUrl() {
        return url;
    }

    public void setCoupleIDPWD(String name, String value) {
       this.coupleIDPWD.put(name, value);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //    coupleIDPWD.put("username", params[0]);
  //  coupleIDPWD.put("password", params[1]);

}
