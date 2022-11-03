package com.liucheng.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liucheng.model.entity.HospitalInfo;
import com.liucheng.service.mapper.HospitalInfoMapper;
import com.liucheng.service.service.HospitalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘呈
 * @createTime 2022/11/3 11:22
 * @description
 */
@Service
public class HospitalInfoServiceImpl extends ServiceImpl<HospitalInfoMapper, HospitalInfo> implements HospitalInfoService {

    @Autowired
    HospitalInfoMapper hospitalInfoMapper;
}
