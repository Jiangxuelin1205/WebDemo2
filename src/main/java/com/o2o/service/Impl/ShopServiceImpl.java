package com.o2o.service.Impl;

import com.o2o.dao.ShopDao;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Shop;
import com.o2o.enums.ShopStateEnum;
import com.o2o.exception.ShopOperationException;
import com.o2o.service.ShopService;
import com.o2o.util.ImageUtil;
import com.o2o.util.PageCalculator;
import com.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;


@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;

    /**
     * 添加商铺信息逻辑：首先对shop做验证，如果为空，则将失败状态返回给controller;
     * 添加商铺
     * 按照商铺ShopId建立图片文件夹
     * 将图片放入商铺的图片文件夹
     * 将图片路径写入数据库
     **/
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        //Shop中的一部分信息需要用户填写：shopName,shopDesc,shopAddr,phone,advice,
        // 在shopService层设置的enableStatus，createTime，lastEditTime 需要程序设置
        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            int effectiveNumber = shopDao.insertShop(shop);
            if (effectiveNumber <= 0) {
                throw new ShopOperationException("商铺信息插入失败");
            } else {
                if (shopImgInputStream != null) {
                    try {
                        //插入图片信息，首先生成图片
                        addShopImg(shop, shopImgInputStream, fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("插入图片信息失败");
                    }
                }
                effectiveNumber = shopDao.updateShop(shop);
                if (effectiveNumber <= 0) {
                    throw new ShopOperationException("更新商铺信息失败");
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.SUCCESS);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //获取图片目录的相对值
        String destination = PathUtil.getShopImgPath(shop.getShopId());
        //获取图片目录的绝对值
        String shopImgAddress = ImageUtil.generateThumbnails(shopImgInputStream, fileName, destination);//生成图片存储的相对地址，并且将图片放入该地址
        shop.setShopImg(shopImgAddress);

    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        //判断是否需要处理图片
        try {
            if (shopImgInputStream != null && fileName != null && !fileName.equals("")) {
                Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                if (tempShop.getShopImg() != null) {
                    ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                }
                addShopImg(shop, shopImgInputStream, fileName);
            }
            //更新店铺信息
            shop.setLastEditTime(new Date());
            int effectedNumber = shopDao.updateShop(shop);
            if (effectedNumber <= 0) {
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            } else {
                shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS, shop);
            }
        } catch (Exception e) {
            throw new ShopOperationException("modify shop error " + e.getMessage());
        }
    }

    @Override
    public ShopExecution getShopList(Shop shop, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        //显示一整页
        List<Shop> shopList = shopDao.queryShopList(shop, rowIndex, pageSize);
        int shopCount = shopDao.queryShopCount(shop);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setCount(shopCount);
            se.setShops(shopList);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }
}
