package com.o2o.service;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {

    @Autowired
    AreaService areaService;

    @Test
    public void testGetAreaList(){
        List<Area> list=areaService.getAreaList();
        System.out.println(list.get(0).getAreaName());
        Assert.assertEquals(list.get(0).getAreaName(),"南苑");
    }
}
