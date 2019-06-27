package com.o2o.service;


import com.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {


    @SuppressWarnings("FieldCanBeLocal")
    public final static String SHOPCATEGORYKEY = "shopcategorylist";

    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
