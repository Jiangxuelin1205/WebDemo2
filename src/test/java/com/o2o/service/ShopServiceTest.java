package com.o2o.service;

import com.o2o.BaseTest;
import com.o2o.dto.ImageHolder;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Area;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.enums.ShopStateEnum;
import com.o2o.exception.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {

    @Autowired
    ShopService shopService;

    @Test
    public void add_shop_test() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");

        File img = new File("./src/main/resources/blackandwhite.jpg");
        InputStream is = new FileInputStream(img);
        ShopExecution se = shopService.addShop(shop, new ImageHolder(img.getName(), is));
        assertEquals(ShopStateEnum.SUCCESS.getState(), se.getState());
    }

    @Test
    public void test_modify_shop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(7L);
        shop.setShopName("修改后的商铺名称");
        File img = new File("./src/main/resources/dabai.jpg");
        InputStream is = new FileInputStream(img);
        ShopExecution shopExecution = shopService.modifyShop(shop, new ImageHolder("dabai.jpg",is));
        System.out.println(shopExecution.getShop().getShopImg());
    }

    @Test
    public void get_shop_list() {
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        Shop shopCondition = new Shop();
        shopCondition.setOwner(owner);
        ShopExecution se = shopService.getShopList(shopCondition, 2, 5);
        System.out.println(se.getShops().size());
        System.out.println(se.getCount());
    }
}
