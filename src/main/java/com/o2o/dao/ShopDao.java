package com.o2o.dao;

import com.o2o.entity.Shop;

public interface ShopDao {

    int insertShop(Shop shop);

    int updateShop(Shop shop);

    Shop queryByShopId(long shopId);
}
