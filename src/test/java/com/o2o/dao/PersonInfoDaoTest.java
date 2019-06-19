package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class PersonInfoDaoTest extends BaseTest {

    @Autowired
    PersonInfoDao personInfoDao;

    @Test
    public void query() {
        PersonInfo result = personInfoDao.queryPersonInfoById(1L);
    }

    @Test
    public void insert() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setEmail("sdf");
        personInfo.setEnableStatus(0);
        personInfo.setLastEditTime(new Date());
        personInfo.setGender(1);
        personInfo.setName("sasdf");
        personInfo.setUserType(1);
        int insert = personInfoDao.insertPersonInfo(personInfo);
    }
}
