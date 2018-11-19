package com.liang.funny.util.Json;


import java.io.Serializable;

public class JsonResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code; //返回码 自定义 如 200,404
    private Boolean state; //状态 一般true,false
    private String message; //消息 例 获取消息成功，获取消息失败
    private Object result; //数据对象

    /**
     * 无参构造
     */
    public JsonResult(){
        super();
    }

    /**
     * 只返回状态、状态码、消息
     */
    public JsonResult(Integer code, Boolean state, String message){
        super();
        this.code = code;
        this.state = state;
        this.message = message;
    }

    /**
     * 返回全部信息 即返回码、状态、消息、数据对象
     */
    public JsonResult(Integer code, Boolean state, String message, Object result){
        super();
        this.code = code;
        this.state = state;
        this.message = message;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
