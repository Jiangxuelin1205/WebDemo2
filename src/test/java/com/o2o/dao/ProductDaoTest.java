package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends BaseTest {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductImgDao productImgDao;

    @Test
    public void insertProduct() {
        Shop shop = new Shop();
        shop.setShopId(2L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);

        Product product = new Product();
        product.setProductName("name");
        product.setProductDesc("desc");
        product.setImgAddr("test");
        product.setNormalPrice("32");
        product.setPromotionPrice("33");
        product.setPriority(2);
        product.setEnableStatus(1);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setShop(shop);
        product.setProductCategory(pc);

        productDao.insertProduct(product);
    }

    @Test
    public void query_product_by_id(){
        Product product=productDao.queryProductById(4);
    }

    @Test
    public void update_product(){
        Product product = new Product();
        ProductCategory pc = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(2L);
        pc.setProductCategoryId(1L);
        product.setProductId(2L);
        product.setShop(shop);
        product.setProductName("第二个产品");
        product.setProductCategory(pc);
        // 修改productId为1的商品的名称
        // 以及商品类别并校验影响的行数是否为1
        int effectedNum = productDao.updateProduct(product);
        assertEquals(effectedNum, 1);
    }


}
