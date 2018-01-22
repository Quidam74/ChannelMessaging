package com.florian.bellanger.channelmessaging;

/**
 * Created by bellangf on 19/01/2018.
 */
public class InfoConnection {
    private String response;
    private int code;
    private String accesstoken;

    @Override
    public String toString() {
        return "InfoConnection{" +
                "reponse='" + response + '\'' +
                ", code=" + code +
                ",accesstoken='" + accesstoken + '\'' +
                '}';
    }

    public InfoConnection(String reponse, int code, String accesstoken) {
        this.response = reponse;
        this.code = code;
        this.accesstoken = accesstoken;
    }

    public void setReponse(String reponse) {

        this.response = reponse;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getReponse() {
        return response;
    }

    public int getCode() {
        return code;
    }

    public String getAccesstoken() {
        return accesstoken;
    }
}
