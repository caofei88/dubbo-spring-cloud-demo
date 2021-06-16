package com.springcloud.dubbo_provider.common;
import java.io.Serializable;
import lombok.Data;
import org.springframework.http.HttpStatus;
import com.alibaba.fastjson.JSON;
@Data
public class DubboResponse<T> implements Serializable {
    /** * */
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * * 返回json对象
     */
    private T data;

    public DubboResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DubboResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> DubboResponse success(String message, T data) {
        String resultStr = JSON.toJSONString(data);
        return new DubboResponse(HttpStatus.OK.value(), message, data);
    }

    public static DubboResponse success(String message) {
        return success(message, null);
    }

    public static DubboResponse error(String message) {
        return new DubboResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    public static DubboResponse error(int code, String message) {
        return new DubboResponse(code, message, null);
    }


}
