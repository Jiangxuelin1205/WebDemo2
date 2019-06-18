package com.o2o.dao;

import com.o2o.entity.Product;

public interface ProductDao {

    int insertProduct(Product product);

    Product queryProductById(long productId);

    int updateProduct(Product product);
}
