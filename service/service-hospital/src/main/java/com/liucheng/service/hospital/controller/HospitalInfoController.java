package com.liucheng.service.hospital.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liucheng.commonutil.result.Result;
import com.liucheng.commonutil.result.ResultCodeEnum;
import com.liucheng.model.entity.HospitalInfo;
import com.liucheng.model.vo.HospitalInfoQueryVO;
import com.liucheng.service.hospital.service.HospitalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 刘呈
 * @createTime 2022/11/3 11:24
 * @description
 */
@RestController
@RequestMapping("/admin/hospital/hospitalInfo")
@Transactional
public class HospitalInfoController {
    @Autowired
    HospitalInfoService hospitalInfoService;

    @GetMapping("findAllHospitalInfo")
    public Result findAllHospitalInfo(){
        List<HospitalInfo> list = hospitalInfoService.list();
        return Result.success(ResultCodeEnum.FIND_SUCCESS,list);
    }

    @DeleteMapping("removeHospitalInfo/{id}")
    public Result removeHospitalInfo(@PathVariable(value = "id")Long id){
        boolean b = hospitalInfoService.removeById(id);
        if(b)return Result.success(ResultCodeEnum.REMOVE_SUCCESS,null);
        return Result.error(ResultCodeEnum.REMOVE_FAILED,null);
    }

    @PostMapping("findPageHospitalInfo/{current}/{size}")
    public Result findPageHospitalInfo(@PathVariable(value = "current")Long current,
                                       @PathVariable(value = "size")Long size,
                                       @RequestBody HospitalInfoQueryVO hospitalInfoQueryVO){
//        创建Page对象
        Page<HospitalInfo> page = new Page<>(current, size);
//        创建查询条件构造器
        QueryWrapper<HospitalInfo> queryWrapper = new QueryWrapper<>();
        String hospitalName = hospitalInfoQueryVO.getHospitalName();
        String hospitalCode = hospitalInfoQueryVO.getHospitalCode();
        if(!StrUtil.isBlankIfStr(hospitalName)){
            queryWrapper.like("hospital_name",hospitalName);
        }
        if(!StrUtil.isBlankIfStr(hospitalCode)){
            queryWrapper.eq("hospital_code",hospitalCode);
        }
//        执行分页查询
        Page<HospitalInfo> result = hospitalInfoService.page(page, queryWrapper);
//        获取并封装数据
        List<HospitalInfo> records = result.getRecords();
        long total = result.getTotal();
        long pages = result.getPages();
        Map<String, Object> param = new HashMap<>();
        param.put("records",records);
        param.put("total",total);
        param.put("pages",pages);
        return Result.success(ResultCodeEnum.PAGE_FIND_SUCCESS,param);
    }

    @PostMapping("saveHospitalInfo")
    public Result saveHospitalInfo(@RequestBody HospitalInfo hospitalInfo){
        hospitalInfo.setStatus(1);
        Random random = new Random();
//        生成0-1000的随机数
        int i = random.nextInt(1000);
//        sign_key使用MD5加密生成（MD5不可逆）
        hospitalInfo.setSignKey(MD5.create().digestHex16(System.currentTimeMillis()+""+i));
        boolean save = hospitalInfoService.save(hospitalInfo);
        if(save)return Result.success(ResultCodeEnum.SAVE_SUCCESS,null);
        return Result.error(ResultCodeEnum.SAVE_FAILED,null);
    }

    @GetMapping("findHospitalInfoById/{id}")
    public Result findHospitalInfoById(@PathVariable(value = "id")Long id){
        HospitalInfo hospitalInfo = hospitalInfoService.getById(id);
        return Result.success(ResultCodeEnum.FIND_SUCCESS,hospitalInfo);
    }

    @PutMapping("updateHospitalInfo")
    public Result updateHospitalInfo(@RequestBody HospitalInfo hospitalInfo){
        boolean b = hospitalInfoService.updateById(hospitalInfo);
        if(b)return Result.success(ResultCodeEnum.UPDATE_SUCCESS,null);
        return Result.error(ResultCodeEnum.UPDATE_FAILED,null);
    }

    @DeleteMapping("batchRemoveHospitalInfo")
    public Result batchRemoveHospitalInfo(@RequestBody List<Long> idList){
        hospitalInfoService.removeBatchByIds(idList);
        return Result.success(ResultCodeEnum.BATCH_REMOVE_SUCCESS,null);
    }

//    医院设置锁定和解锁
    @PutMapping("lockHospitalInfo/{id}/{status}")
    public Result lockHospitalInfo(@PathVariable(value = "id")Long id,
                                   @PathVariable(value = "status")Integer status){
        HospitalInfo hospitalInfo = hospitalInfoService.getById(id);
        hospitalInfo.setStatus(status);
        hospitalInfoService.updateById(hospitalInfo);
        return Result.success(ResultCodeEnum.UPDATE_SUCCESS,null);
    }
}
