package com.o2o.service;

import com.o2o.BaseTest;
import com.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineServiceTest extends BaseTest {

    @Autowired
    HeadLineService headLineService;

    @Test
    public void headLineList() {
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setLineId(1L);
        List<HeadLine> headLines = headLineService.getHeadLineList(headLineCondition);
        System.out.println(headLines.size());
    }

}
