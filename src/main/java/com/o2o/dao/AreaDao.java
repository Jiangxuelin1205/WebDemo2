package com.o2o.dao;

import com.o2o.entity.Area;

import java.util.List;


public interface AreaDao {
    //返回列出区域列表
     List<Area> queryArea();
}
