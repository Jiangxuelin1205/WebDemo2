package com.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.Shop;
import com.o2o.enums.ShopStateEnum;
import com.o2o.service.ShopService;
import com.o2o.util.HttpServletRequestUtil;
import com.o2o.util.ImageUtil;
import com.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    ShopService shopService;

    @RequestMapping(value="/registershop",method=RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();

        //从request中获取商铺信息
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );//解析文件上传,从当前session的上下文中获取上传内容
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            //报错
            modelMap.put("success", false);
            modelMap.put("errorMessage", "上传图片不能为空");
            return modelMap;
        }
        //注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);//todo:硬编码，以后会完善
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());//shopImg报错，因为addShop接收的是一个File类型的参数。
                //CommonsMultipartFile中有函数getInputStream，我们可以利用该函数将CommonsMultipartFile转换成为一个File类型的变量
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errorMessage", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errorMessage", se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMessage", "请输入店铺信息");
            return modelMap;
        }

    }
}
