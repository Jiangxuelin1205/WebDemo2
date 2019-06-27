package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AreaDaoTest extends BaseTest {
    @Autowired
    AreaDao areaDao;

    @Test
    public void query_area() {
        List<Area> areaList = areaDao.queryArea();
        Assert.assertEquals(3, areaList.size());
    }

    @Test
    public void insert_area() {
        Area area = new Area();
        area.setAreaName("asdfa");
        area.setPriority(2);
        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        Assert.assertEquals(1, areaDao.insertArea(area));
    }

    @Test
    public void update_area() {
        Area area = new Area();
        area.setAreaId(3);
        area.setAreaName("asddfsdfa");
        area.setPriority(2);
        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        Assert.assertEquals(1, areaDao.updateArea(area));
    }

    @Test
    public void delete() {
        Assert.assertEquals(1, areaDao.deleteArea(3L));
    }

    @Test
    public void batchDelete() {
        List<Long> areaIds = new ArrayList<>();
        areaIds.add(3L);
        areaIds.add(4L);
        Assert.assertEquals(2, areaDao.batchDeleteArea(areaIds));
    }
}
