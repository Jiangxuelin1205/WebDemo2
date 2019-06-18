package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductImgDaoTest extends BaseTest {

    @Autowired
    ProductImgDao productImgDao;

    @Test
    public void test(){
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(2L);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(2L);
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2, effectedNum);
    }

    @Test
    public void delete(){
        int effectedNumber=productImgDao.deleteProductImgByProductId(4L);
        System.out.println(effectedNumber);
    }

    @Test
    public void query_product_img_list(){
        List<ProductImg> images=productImgDao.queryProductImgList(2L);
        System.out.println(images.size());
    }
}
