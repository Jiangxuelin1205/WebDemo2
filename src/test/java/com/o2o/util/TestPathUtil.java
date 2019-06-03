package com.o2o.util;

import org.junit.Test;

public class TestPathUtil {

    @Test
    public void testPathUtil(){
        String path=PathUtil.getImgBasePath();
        System.out.println(path);
    }

}
