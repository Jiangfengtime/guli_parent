package com.hjf.commonutils;

/**
 * @author Jiang锋时刻
 * @create 2020-09-30 21:54
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果类型
 *
 * 返回值类型
 *  {
 *      "success": 布尔, //响应是否成功
 *      "code": 数字, //响应码
 *      "message": 字符串, //返回消息
 *      "data": HashMap //返回数据，放在键值对中
 *  }
 */

@Data
public class ReturnType {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "响应码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    // 把构造方法私有
    private ReturnType(){};

    // 成功静态方法
    public static ReturnType ok(){
        ReturnType returnType = new ReturnType();
        returnType.setSuccess(true);
        returnType.setCode(ResultCode.SUCCESS);
        returnType.setMessage("成功");
        return returnType;
    }

    // 失败静态方法
    public static ReturnType error(){
        ReturnType returnType = new ReturnType();
        returnType.setSuccess(false);
        returnType.setCode(ResultCode.ERROR);
        returnType.setMessage("失败");
        return returnType;
    }

    public ReturnType success(Boolean success) {
        this.setMessage(message);
        return this;        // 当前类的对象
    }

    public ReturnType message(String message){
        this.setMessage(message);
        return this;
    }

    public ReturnType code(Integer code){
        this.setCode(code);
        return this;
    }

    public ReturnType data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ReturnType data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
