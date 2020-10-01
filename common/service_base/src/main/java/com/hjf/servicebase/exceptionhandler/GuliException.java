package com.hjf.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiang锋时刻
 * @create 2020-10-01 10:50
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("异常信息")
    private String msg;
}
