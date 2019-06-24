package com.o2o.service.Impl;

import com.o2o.dao.LocalAuthDao;
import com.o2o.dto.LocalAuthExecution;
import com.o2o.entity.LocalAuth;
import com.o2o.enums.LocalAuthStateEnum;
import com.o2o.exception.LocalAuthOperationException;
import com.o2o.service.LocalAuthService;
import com.o2o.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    @Autowired
    LocalAuthDao localAuthDao;

    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(userName, MD5.getMD5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Override
    @Transactional
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUserName() == null ||
                localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        LocalAuth tempLocalAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
        if (tempLocalAuth != null) {
            return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
        }
        try {
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            localAuth.setPassword(MD5.getMD5(localAuth.getPassword()));
            int effectedNumber = localAuthDao.insertLocalAuth(localAuth);

            if (effectedNumber <= 0) {
                throw new LocalAuthOperationException("账号绑定失败");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("插入本地账号信息失败");
        }
    }

    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
        if (userId == null || username == null || password == null || newPassword == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            int effectedNumber = localAuthDao.updateLocalAuth(userId, username, MD5.getMD5(password), MD5.getMD5(newPassword), new Date());
            if (effectedNumber <= 0) {
                throw new LocalAuthOperationException("更新密码失败");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("更新密码失败" + e.getMessage());
        }
    }
}
