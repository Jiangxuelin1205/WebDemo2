package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void query_product_category_list_test() {
        List<ProductCategory> list = productCategoryDao.queryProductCategoryList(2L);
        System.out.println(list.size());
    }


}
