package com.example.myapplication.NetworkApi.CheckVersion;

public class ResponseVersion {
    private int code;
    private String message;
    private String version;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ResponseVersion(int code, String message, String version) {
        this.code = code;
        this.message = message;
        this.version = version;
    }
}
