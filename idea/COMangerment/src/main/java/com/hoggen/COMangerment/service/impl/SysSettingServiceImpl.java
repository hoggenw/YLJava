package com.hoggen.COMangerment.service.impl;

import com.hoggen.COMangerment.dao.SysSettingDao;
import com.hoggen.COMangerment.entity.SysSetting;
import com.hoggen.COMangerment.service.SysSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SysSettingServiceImpl implements SysSettingService {


    @Autowired
    private SysSettingDao sysSettingDao;


    @Override
    public List<?>   find() {

        List<?> model =  sysSettingDao.find(100001012L);

        return model;
    }

    @Override
    public Integer updateById(SysSetting sysConfigEntity) {
        return sysSettingDao.updateById(sysConfigEntity);
    }
}
