package com.o2o.dao;

import com.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    int insertShop(Shop shop);

    int updateShop(Shop shop);

    Shop queryByShopId(long shopId);

    /**
     * 分页查询店铺，可输入的条件有：店铺名称，店铺状态，店铺类别，区域ID，owner
     * 这些信息全都携带在shopCondition变量中
     **/
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") Integer rowIndex,
                             @Param("pageSize") Integer pageSize);

    /**
     * 由于需要进行分页，因此需要查询具有某一个限定信息的商铺的总数，然后 查询总数/pageSize次
     **/
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

}
