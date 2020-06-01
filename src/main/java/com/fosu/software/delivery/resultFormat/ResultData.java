package com.fosu.software.delivery.resultFormat;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.resultFormat
 * @Author: Boss_king
 * @CreateTime: 2020-06-01 19:15
 * @Description: 返回结果格式类
 */
public class ResultData<T> {
    // 信息状态码，200表示成功，1xx表示失败
    private Integer code;
    // 提示信息
    private String msg;
    // 返回的对象
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
