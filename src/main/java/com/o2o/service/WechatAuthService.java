package com.o2o.service;

import com.o2o.dto.WechatAuthExecution;
import com.o2o.entity.WechatAuth;
import com.o2o.exception.WechatAuthOperationException;

public interface WechatAuthService {

    WechatAuth getWechatAuthByOpenId(String openId);


    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
