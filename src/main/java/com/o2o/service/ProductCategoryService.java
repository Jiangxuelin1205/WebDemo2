package com.o2o.service;

import com.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategory(long shopId);
}
