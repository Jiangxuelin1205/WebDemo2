package com.o2o.service;

import com.o2o.dto.ImageHolder;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Shop;
import com.o2o.exception.ShopOperationException;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Service层需要调用dao层，以及增加一些处理事务的操作
 * 因为使用了事务，因此抛出异常的时候必须使用Runtime Exception的异常类，这样事务失败能够进行回滚
 */
public interface ShopService {

    ShopExecution addShop(Shop shop, ImageHolder thumbnail);

    Shop getByShopId(long shopId);

    ShopExecution modifyShop(Shop shop,ImageHolder thumbnail)throws ShopOperationException;

    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

}
