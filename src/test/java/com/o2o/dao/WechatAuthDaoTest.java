package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.WechatAuth;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WechatAuthDaoTest extends BaseTest {

    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    public void testAInsertWechatAuth() throws Exception {
        // 新增一条微信帐号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        // 给微信帐号绑定上用户信息
        wechatAuth.setPersonInfo(personInfo);
        // 随意设置上openId
        wechatAuth.setOpenId("dafahizhfdhaih");
        wechatAuth.setCreateTime(new Date());
        int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryWechatAuthByOpenId() throws Exception {
        WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("dafahizhfdhaih");
        assertEquals("testusername", wechatAuth.getPersonInfo().getName());
    }
}
