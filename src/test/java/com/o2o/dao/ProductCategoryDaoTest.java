package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void query_product_category_list_test() {
        List<ProductCategory> list = productCategoryDao.queryProductCategoryList(2L);
        System.out.println(list.size());
    }

    @Test
    public void batch_insert_product_category_test() {
        List<ProductCategory> list = new ArrayList<>();
        ProductCategory p1 = new ProductCategory();
        p1.setProductCategoryName("new1");
        p1.setPriority(2);
        p1.setCreateTime(new Date());
        p1.setShopId(2L);

        ProductCategory p2 = new ProductCategory();
        p2.setPriority(3);
        p2.setProductCategoryName("new2");
        p2.setCreateTime(new Date());
        p2.setShopId(2L);

        list.add(p1);
        list.add(p2);
        System.out.println(productCategoryDao.batchInsertProductCategory(list));
    }

    @Test
    public void delete(){
        System.out.println(productCategoryDao.deleteProductCategory(2,2));
    }
}
