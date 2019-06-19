package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.HeadLine;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void query_head_line() {
        List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
        assertEquals(5, headLineList.size());
    }

    @Test
    public void query_head_line_by_id() {
        HeadLine headLine = headLineDao.queryHeadLineById(2L);
    }

    @Test
    public void query_head_line_by_ids() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        List<HeadLine> headLines = headLineDao.queryHeadLineByIds(ids);
        Assert.assertEquals(headLines.size(), 3);
    }

    @Test
    public void insert_head_line() {
        HeadLine headLine = new HeadLine();
        headLine.setLineImg("test");
        headLine.setCreateTime(new Date());
        headLine.setLastEditTime(new Date());
        headLine.setEnableStatus(1);
        headLine.setLineName("sdf");
        headLine.setPriority(0);
        headLine.setLineLink("wefwef");
        int effectedNumber = headLineDao.insertHeadLine(headLine);
        Assert.assertEquals(effectedNumber, 1);
    }

    @Test
    public void update_head_line() {
        HeadLine headLine = new HeadLine();
        headLine.setPriority(1);
        headLine.setLineName("sdfasd");
        headLine.setLineId(2L);
        int effectedNumber = headLineDao.updateHeadLine(headLine);
        Assert.assertEquals(effectedNumber, 1);
    }

    @Test
    public void delete_head_line() {
        int effectedNumber = headLineDao.deleteHeadLine(2L);
        Assert.assertEquals(effectedNumber, 1);
    }

    @Test
    public void delete_batch() {
        List<Long> deleteBatch = new ArrayList<>();
        deleteBatch.add(3L);
        deleteBatch.add(4L);
        int effectedNumber = headLineDao.batchDeleteHeadLine(deleteBatch);
        Assert.assertEquals(effectedNumber, 2);
    }
}
