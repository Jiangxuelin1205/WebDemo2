package com.o2o.service.Impl;

import com.o2o.dao.ProductCategoryDao;
import com.o2o.dto.ProductCategoryExecution;
import com.o2o.entity.ProductCategory;
import com.o2o.enums.ProductCategoryStateEnum;
import com.o2o.exception.ProductCategoryOperationException;
import com.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategory(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList == null || productCategoryList.size() == 0) {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
        try {
            int effectedNumber = productCategoryDao.batchInsertProductCategory(productCategoryList);
            if (effectedNumber > 0) {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            } else {
                throw new ProductCategoryOperationException("店铺类别创建失败");
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("Batch Add Product Category " + e.getMessage());
        }
    }

    /**
     * 调用dao层方法
     **/
    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        if (productCategoryId <= 0 || shopId <= 0) {
            throw new ProductCategoryOperationException("商品类别信息错误或者店铺信息错误");
        }
        try {
            int effectedNumber = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectedNumber <= 0) {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("delete Product Category error :" + e.getMessage());
        }
    }

}
