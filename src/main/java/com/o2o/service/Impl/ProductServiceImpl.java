package com.o2o.service.Impl;

import com.o2o.dao.ProductDao;
import com.o2o.dao.ProductImgDao;
import com.o2o.dto.ImageHolder;
import com.o2o.dto.ProductExecution;
import com.o2o.entity.Product;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.ProductImg;
import com.o2o.enums.ProductStateEnum;
import com.o2o.exception.ProductOperationException;
import com.o2o.service.ProductService;
import com.o2o.util.ImageUtil;
import com.o2o.util.PageCalculator;
import com.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductImgDao productImgDao;

    @Transactional
    @Override
    //设置product的缩略图信息和product的详细信息。imageHolderList是指product的商品详清，
    public ProductExecution addProduct(Product product, ImageHolder thumbNail, List<ImageHolder> imageHolderList) throws ProductOperationException {
        if (product == null || product.getShop() == null || product.getShop().getShopId() == null) {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(1);
        //处理缩略图，将缩略图的相对路径赋给product
        addImage(product, thumbNail);
        //往tb_product写入信息，并且获得productId
        try {
            int effectedNumber = productDao.insertProduct(product);//此时productId已经被写入product中
            if (effectedNumber <= 0) {
                throw new ProductOperationException("商品添加失败");
            }
        } catch (Exception e) {
            throw new ProductOperationException("商品添加失败");
        }

        //此时product中已经被写入productId，结合imageHolderList处理商品详情图
        if (imageHolderList != null && imageHolderList.size() > 0) {
            addProductImgList(product, imageHolderList);
        }
        return new ProductExecution(ProductStateEnum.SUCCESS);
    }

    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        if (product == null || product.getShop() == null || product.getShop().getShopId() == null) {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
        product.setLastEditTime(new Date());
        if (thumbnail != null) {
            Product tempProduct = productDao.queryProductById(product.getProductId());
            if (tempProduct.getImgAddr() != null) {
                ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
            }
            addImage(product, thumbnail);
        }
        if (productImgHolderList != null && productImgHolderList.size() > 0) {
            deleteProductImgList(product.getProductId());
            addProductImgList(product, productImgHolderList);
        }
        try {
            int effectedNumber = productDao.updateProduct(product);
            if (effectedNumber <= 0) {
                throw new ProductOperationException("商品信息更新失败");
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } catch (Exception e) {
            throw new ProductOperationException("商品信息更新失败:" + e.toString());
        }
    }

    private void deleteProductImgList(Long productId) {
        List<ProductImg> productImgs = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgs) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    @Transactional
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> products = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setCount(count);
        pe.setProductList(products);
        return pe;
    }

    private void addProductImgList(Product product, List<ImageHolder> images) {
        String destination = PathUtil.getShopImgPath(product.getShop().getShopId());
        List<ProductImg> productImageList = new ArrayList<>();
        //遍历images，将其转换成为productImg之后，存入productImgList中，方便之后批量插入图片
        for (ImageHolder image : images) {
            String imgAddr = ImageUtil.generateNormalImage(image, destination);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setCreateTime(new Date());
            productImg.setProductId(product.getProductId());
            productImageList.add(productImg);
        }
        if (productImageList.size() > 0) {
            try {
                int effectedNumber = productImgDao.batchInsertProductImg(productImageList);
                if (effectedNumber <= 0) {
                    throw new ProductOperationException("创建商品详细图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详细图片失败 " + e.getMessage());
            }
        }
    }

    private void addImage(Product product, ImageHolder thumbNail) {
        String destination = PathUtil.getShopImgPath(product.getShop().getShopId());
        String productImgAddr = ImageUtil.generateThumbnails(thumbNail, destination);
        product.setImgAddr(productImgAddr);
    }
}
