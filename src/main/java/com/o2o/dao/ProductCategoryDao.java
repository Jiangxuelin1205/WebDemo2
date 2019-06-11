package com.o2o.dao;

import com.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {

    List<ProductCategory> queryProductCategoryList(long shopId);

    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    //TODO：再删除指定的productCategory之后，还有一些具有副作用的操作。。。想不起来了
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
