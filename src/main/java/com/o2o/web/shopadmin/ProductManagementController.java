package com.o2o.web.shopadmin;


import com.o2o.dto.ProductCategoryExecution;
import com.o2o.dto.Result;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.Shop;
import com.o2o.enums.ProductCategoryStateEnum;
import com.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {

    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        //从request中获取shopId
      /*  Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;*/
        Shop currentShop = new Shop();
        currentShop.setShopId(2L);
        List<ProductCategory> list = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            //能够成功获取商铺信息
            list = productCategoryService.getProductCategory(currentShop.getShopId());
            return new Result<>(true, list);
        } else {
            ProductCategoryStateEnum se = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<>(false, se.getStateInfo(), se.getState());
        }
    }
}
