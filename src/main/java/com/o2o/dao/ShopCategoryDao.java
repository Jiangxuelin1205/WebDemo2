package com.o2o.dao;

import com.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {

    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")
                                         ShopCategory shopCategoryCondition);//传入参数，根据传入的参数选择出商铺的类别

    /**
     * 通过Id返回唯一的店铺类别信息
     */
    ShopCategory queryShopCategoryById(long shopCategoryId);

    /**
     * 根据传入的Id列表查询店铺类别信息(供超级管理员选定删除类别的时候用，主要是处理图片)
     *
     */
    List<ShopCategory> queryShopCategoryByIds(List<Long> shopCategoryIdList);

    /**
     * 插入一条店铺类别信息
     */
    int insertShopCategory(ShopCategory shopCategory);

    /**
     * 更新店铺类别信息
     */
    int updateShopCategory(ShopCategory shopCategory);

    /**
     * 删除店铺类别
     */
    int deleteShopCategory(long shopCategoryId);

    /**
     * 批量删除店铺类别
     */
    int batchDeleteShopCategory(List<Long> shopCategoryIdList);
}
