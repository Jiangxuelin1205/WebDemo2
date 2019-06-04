package com.o2o.dao;

import com.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {

    List<ShopCategory> queryShopCategory(@Param("ShopCategoryCondition")
                                         ShopCategory shopCategoryCondition);//传入参数，根据传入的参数选择出商铺的类别
}
