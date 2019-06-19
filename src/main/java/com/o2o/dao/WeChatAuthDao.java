package com.o2o.dao;

import com.o2o.entity.WechatAuth;

public interface WeChatAuthDao {

    /**
     * 通过openId查询对应本平台的微信帐号
     */
    WechatAuth queryWechatInfoByOpenId(String openId);

    /**
     * 添加对应本平台的微信帐号
     */
    int insertWechatAuth(WechatAuth wechatAuth);
}
