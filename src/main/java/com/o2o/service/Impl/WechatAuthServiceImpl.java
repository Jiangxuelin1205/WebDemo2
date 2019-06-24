package com.o2o.service.Impl;

import com.o2o.dao.PersonInfoDao;
import com.o2o.dao.WechatAuthDao;
import com.o2o.dto.WechatAuthExecution;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.WechatAuth;
import com.o2o.enums.WechatAuthStateEnum;
import com.o2o.exception.WechatAuthOperationException;
import com.o2o.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {

    private static Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);
    @Autowired
    private WechatAuthDao wechatAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
        if (wechatAuth == null || wechatAuth.getOpenId() == null) {
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            wechatAuth.setCreateTime(new Date());
            if (wechatAuth.getPersonInfo() != null && wechatAuth.getWechatAuthId() == null) {
                //用户第一次使用该平台
                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectedNumber = personInfoDao.insertPersonInfo(personInfo);
                    wechatAuth.setPersonInfo(personInfo);
                    if (effectedNumber <= 0) {
                        throw new WechatAuthOperationException("创建用户信息失败");
                    }

                } catch (Exception e) {
                    throw new WechatAuthOperationException("创建用户信息失败");
                }
            }
        } catch (Exception e) {
            log.error("insertWechatAuth error:" + e.toString());
            throw new WechatAuthOperationException("insertWechatAuth error: " + e.getMessage());
        }

        int effectedNumber = wechatAuthDao.insertWechatAuth(wechatAuth);
        if (effectedNumber <= 0) {
            throw new WechatAuthOperationException("帐号创建失败");
        } else {
            return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
        }
    }
}
