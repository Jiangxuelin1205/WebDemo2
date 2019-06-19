package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.ShopCategory;
import org.codehaus.jackson.map.Serializers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
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

    @Test
    public void test_query_shop_category_by_id(){
        ShopCategory shopCategory=shopCategoryDao.queryShopCategoryById(1L);
    }

    @Test
    public void test_query_shop_category_by_ids(){
        List<Long> ids=new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        List<ShopCategory> shopCategories=shopCategoryDao.queryShopCategoryByIds(ids);
        System.out.println(shopCategories.size());
    }

    @Test
    public void insert_shop_category(){
       ShopCategory shopCategory=new ShopCategory();
       shopCategory.setParent(null);
       shopCategory.setCreateTime(new Date());
       shopCategory.setShopCategoryImg("sdf");
        int effectedNumber=shopCategoryDao.insertShopCategory(shopCategory);
    }

    @Test
    public void delete_shop_category(){
        int effectedNumber=shopCategoryDao.deleteShopCategory(2L);
    }

    @Test
    public void batchDelete(){
        List<Long> ids=new ArrayList<>();
        ids.add(3L);
        int effectedNumber=shopCategoryDao.batchDeleteShopCategory(ids);
    }
}
