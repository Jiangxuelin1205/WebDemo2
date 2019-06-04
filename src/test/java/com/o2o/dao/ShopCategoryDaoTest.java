package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.ShopCategory;
import org.codehaus.jackson.map.Serializers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    public ShopCategoryDao shopCategoryDao;

    @Test
    public void test_shopcategory_dao_with_empty_parent(){
        List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(new ShopCategory());
        Assert.assertEquals(2,shopCategoryList.size());
    }

    @Test
    public void test_shopcategory_dao_with_parent(){
        ShopCategory parentCategory=new ShopCategory();
        parentCategory.setShopCategoryId(1L);
        ShopCategory testCategory=new ShopCategory();
        testCategory.setParent(parentCategory);
        List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(testCategory);
        Assert.assertEquals(1,shopCategoryList.size());
    }
}
