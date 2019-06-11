package com.o2o.dao;

import com.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    List<ProductCategory> queryProductCategoryList(long shopId);

    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
}
