package com.mrmilu.pojo;

/**
 *
 * 统一响应格式
 */
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //带数据响应
    public static Result success(Object data){
        return new Result(1,"success",data);
    }

    //不带数据响应
    public static Result success(){
        return new Result(1,"success",null);
    }

    //请求失败
    public static Result error(String msg){
        return new Result(0,msg,null);
    }

    /**
     * 获取
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "Result{\ncode = " + code + ", \nmsg = " + msg + ",\n data = " + data + "}";
    }
}
