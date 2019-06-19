package com.o2o.dao;

import com.o2o.entity.Area;

import java.util.List;


public interface AreaDao {

    /**
     * 返回列出区域列表
     */
    List<Area> queryArea();


    int insertArea(Area area);

    /**
     * 更新区域信息
     */
    int updateArea(Area area);

    /**
     * 删除区域信息
     */
    int deleteArea(long areaId);

    /**
     * 批量删除区域列表
     */
    int batchDeleteArea(List<Long> areaIdList);
}
