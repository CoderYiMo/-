package com.liucheng.commonutil.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘呈
 * @createTime 2022/11/3 11:37
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static<T> Result<T> success(String message,T data){
        return new Result(200,message,data);
    }

    public static<T> Result<T> success(Integer code,String message,T data){
        return new Result(code,message,data);
    }

    public static<T> Result<T> success(ResultCodeEnum rEnum,T data){
        return new Result(rEnum.getCode(), rEnum.getMessage(),data);
    }

    public static<T> Result<T> error(Integer code,String message,T data){
        return new Result(code,message,data);
    }

    public static<T> Result<T> error(ResultCodeEnum rEnum,T data){
        return new Result(rEnum.getCode(), rEnum.getMessage(),data);
    }
}
