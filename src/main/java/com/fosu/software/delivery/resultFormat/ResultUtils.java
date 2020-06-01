package com.fosu.software.delivery.resultFormat;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.resultFormat
 * @Author: Boss_king
 * @CreateTime: 2020-06-01 19:16
 * @Description: 返回格式的工具类
 */
public class ResultUtils {
    // 统一结果集对象
    private static ResultData<Object> result = new ResultData<>();
    /*
    * 返回成功时调用的参数
    * @Param object 表示传入的参数*/
    public static ResultData<Object> success(Object object){
        result.setCode(200);
        result.setMsg("success");
        result.setData(object);
        return result;
    }
    /*返回成功时，无参数*/
    public static ResultData<Object> success(){
        return success(null);
    }
    /*返回失败时
    * code:要返回的状态码
    * msg:返回的信息*/
    public static ResultData<Object> fail(Integer code, String msg) {
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
