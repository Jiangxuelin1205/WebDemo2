package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.Product;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ProductDaoTest extends BaseTest {

    @Autowired
    ProductDao productDao;

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
}
