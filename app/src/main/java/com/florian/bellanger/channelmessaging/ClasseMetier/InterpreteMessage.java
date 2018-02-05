package com.florian.bellanger.channelmessaging.ClasseMetier;

/**
 * Created by bellangf on 05/02/2018.
 */
public class InterpreteMessage {



    private String response;
    private String code;

    public InterpreteMessage(String response, String code) {
        this.setCode(code);
        this.setResponse(response);
    }

    @Override
    public String toString() {
        return "InterpreteMessage{" +
                "response='" + response + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
