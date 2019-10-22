package com.hoggen.COMangerment.service;

import com.hoggen.COMangerment.entity.SysSetting;

import java.util.List;

public interface SysSettingService {
    /**
     * 获取一条指定的数据
     * @param
     * @return
     */
    public List<?>   find( );




    /**
     * 更新一条指定数据
     * @param
     * @return
     */
    public Integer updateById(SysSetting sysConfigEntity);

}
