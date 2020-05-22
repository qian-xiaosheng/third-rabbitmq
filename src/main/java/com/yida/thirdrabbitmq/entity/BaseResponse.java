package com.yida.thirdrabbitmq.entity;

public class BaseResponse {
    private int code;
    private String data;

    public BaseResponse(Boolean result) {
        if (result) {
            this.code = 200;
            this.data = "OK";
        } else {
            this.code = 400;
            this.data = "fail";
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", data='" + data + '\'' +
                '}';
    }
}
