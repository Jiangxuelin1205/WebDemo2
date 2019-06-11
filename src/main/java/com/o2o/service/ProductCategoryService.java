package com.o2o.service;

import com.o2o.dto.ProductCategoryExecution;
import com.o2o.entity.ProductCategory;
import com.o2o.exception.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategory(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;
}
