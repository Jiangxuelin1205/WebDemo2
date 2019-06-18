package com.o2o.service;

import com.o2o.dto.ImageHolder;
import com.o2o.dto.ProductExecution;
import com.o2o.entity.Product;
import com.o2o.exception.ProductOperationException;

import java.util.List;

public interface ProductService {

    ProductExecution addProduct(Product product, ImageHolder thumbNail,
                                List<ImageHolder> imageHolderList) throws ProductOperationException;

    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException;

    Product getProductById(int productId);

}
