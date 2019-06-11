package com.o2o.service;

import com.o2o.BaseTest;
import com.o2o.dto.ProductCategoryExecution;
import com.o2o.entity.ProductCategory;
import com.o2o.exception.ProductCategoryOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCategoryServiceTest extends BaseTest {

    @Autowired
    ProductCategoryService productCategoryService;

    @Test
    public void query_product_category_test() {
        List<ProductCategory> shopCategories = productCategoryService.getProductCategory(2L);
        System.out.println(shopCategories.size());
    }

    @Test
    public void batch_insert() throws ProductCategoryOperationException {
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

        ProductCategoryExecution pc = productCategoryService.batchAddProductCategory(list);
        System.out.println(pc.getState());
        System.out.println(pc.getStateInfo());
    }

    @Test
    public void delete() throws ProductCategoryOperationException {
        ProductCategoryExecution pc=productCategoryService.deleteProductCategory(3,2);
        System.out.println(pc.getState());
    }
}
