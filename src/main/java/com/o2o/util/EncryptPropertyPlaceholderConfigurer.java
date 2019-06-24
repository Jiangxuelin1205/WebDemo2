package com.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] encryptPropertyNames = {"jdbc.username", "jdbc.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            return DESUtil.getDecryptString(propertyValue);
        } else {
            return propertyValue;
        }
    }

    private boolean isEncryptProp(String propertyName) {
        // 若等于需要加密的field，则进行加密
        for (String encryptpropertyName : encryptPropertyNames) {
            if (encryptpropertyName.equals(propertyName))
                return true;
        }
        return false;
    }
}
