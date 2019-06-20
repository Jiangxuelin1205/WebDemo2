package com.o2o.web.shopadmin;


import com.o2o.dto.ProductCategoryExecution;
import com.o2o.dto.Result;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.Shop;
import com.o2o.enums.ProductCategoryStateEnum;
import com.o2o.exception.ProductCategoryOperationException;
import com.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {

    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        //从request中获取shopId
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;
       /* Shop currentShop = new Shop();
        currentShop.setShopId(2L);
        List<ProductCategory> list = null;*/
        if (currentShop != null && currentShop.getShopId() > 0) {
            //能够成功获取商铺信息
            list = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<>(true, list);
        } else {
            ProductCategoryStateEnum se = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<>(false, se.getStateInfo(), se.getState());
        }
    }

    //todo:待测试
    @RequestMapping(value = "/addproductcategories", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProductCategories(@RequestBody List<ProductCategory> productCategories,
                                                     HttpServletRequest request) {
        //设置返回结果
        Map<String, Object> modelMap = new HashMap<>();
        //从session中获取shopId
        long shopId = (int) request.getSession().getAttribute("shopId");
        if (productCategories == null || productCategories.size() == 0) {//保卫语句
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
            return modelMap;
        }
        for (ProductCategory pc : productCategories) {
            pc.setShopId(shopId);
        }
        try {
            ProductCategoryExecution pc = productCategoryService.batchAddProductCategory(productCategories);
            if (pc.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", pc.getStateInfo());
            }
            return modelMap;

        } catch (ProductCategoryOperationException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
    }

    @RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productCategoryId <= 0 || request == null) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少选择一个商品类别");
            return modelMap;
        } else {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pc = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
                if (pc.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pc.getStateInfo());
                }
                return modelMap;
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        }
    }
}
