package com.o2o.service;

import com.o2o.BaseTest;
import com.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryServiceTest extends BaseTest {

    @Autowired
    ProductCategoryService productCategoryService;

    @Test
    public void query_product_category_test(){
        List<ProductCategory> shopCategories=productCategoryService.getProductCategory(2L);
        System.out.println(shopCategories.size());
    }
}
