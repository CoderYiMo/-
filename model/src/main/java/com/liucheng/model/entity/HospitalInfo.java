package com.liucheng.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.liucheng.model.base.BaseEntity;
import lombok.Data;

/**
 * @author 刘呈
 * @createTime 2022/11/3 10:41
 * @description
 */
@Data
@TableName(value = "hospital_info")
public class HospitalInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String hospitalName;

    private String hospitalCode;

    private String apiUrl;

    private String signKey;

    private String contactsName;

    private String contactsPhone;

    private Integer status;
}
