package com.o2o.service;

import com.o2o.dto.LocalAuthExecution;
import com.o2o.entity.LocalAuth;
import com.o2o.exception.LocalAuthOperationException;

public interface LocalAuthService {

    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

    LocalAuth getLocalAuthByUserId(long userId);

    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
            throws LocalAuthOperationException;
}
