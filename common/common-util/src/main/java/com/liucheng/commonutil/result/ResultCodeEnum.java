package com.liucheng.commonutil.result;

import lombok.Data;
import lombok.Getter;

/**
 * @author 刘呈
 * @createTime 2022/11/3 11:42
 * @description
 */
public enum ResultCodeEnum {
    //成功
    SUCCESS(200,"成功"),
    REMOVE_SUCCESS(201,"删除成功"),
    SAVE_SUCCESS(202,"添加成功"),
    FIND_SUCCESS(203,"查询成功"),
    PAGE_FIND_SUCCESS(204,"分页查询成功"),
    UPDATE_SUCCESS(205,"更新成功"),
    BATCH_REMOVE_SUCCESS(206,"批量删除成功"),

    //失败
    FAILED(500,"失败"),
    REMOVE_FAILED(501,"删除失败"),
    SAVE_FAILED(502,"添加失败"),
    UPDATE_FAILED(505,"更新失败");


    @Getter
    private Integer code;

    @Getter
    private String message;

    private ResultCodeEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
