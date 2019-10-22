package com.hoggen.COMangerment.dao;

import com.hoggen.COMangerment.entity.SysSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSettingDao {

    /**
     * 获取一条指定的数据
     * @param
     * @return
     */
    public List<?>  find(@Param("id") Long id);

    /**
     * 更新一条指定数据
     * @param
     * @return
     */
    public Integer updateById(SysSetting sysConfigEntity);
}
