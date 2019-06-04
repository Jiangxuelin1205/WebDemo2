package com.o2o.service;

import com.o2o.BaseTest;
import com.o2o.entity.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryServiceTest extends BaseTest {

    @Autowired
    ShopCategoryService shopCategoryService;

    @Test
    public void shop_category_service_test(){
        List<ShopCategory> shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
        Assert.assertEquals(shopCategoryList.size(),1);
    }


}
