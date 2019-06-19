package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.LocalAuth;
import com.o2o.entity.PersonInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class LocalAuthDaoTest extends BaseTest {

    @Autowired
    LocalAuthDao localAuthDao;

    @Test
    public void query_local_user_by_name_and_password() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd("f", "f");
    }

    @Test
    public void query_local_by_userId() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
    }

    @Test
    public void insert() {
        LocalAuth localAuth = new LocalAuth();
        localAuth.setUserName("sdf");
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setPersonInfo(personInfo);
        localAuth.setPassword("sdff");
        localAuth.setCreateTime(new Date());
        int effectedNumber = localAuthDao.insertLocalAuth(localAuth);
        Assert.assertEquals(effectedNumber, 1);
    }

    @Test
    public void update(){
        int effectedNumber=localAuthDao.updateLocalAuth(1L,"f","f","fff",new Date());
    }
}
