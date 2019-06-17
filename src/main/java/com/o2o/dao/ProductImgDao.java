package com.o2o.dao;

import com.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

    /**
     * 批量添加商品详情图片
    **/
    int batchInsertProductImg(List<ProductImg> productImgList);
}
