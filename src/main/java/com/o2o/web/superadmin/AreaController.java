package com.o2o.web.superadmin;

import com.o2o.entity.Area;
import com.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/superadmin")
public class AreaController {

    @Autowired
    AreaService areaService;

    private Logger logger= LoggerFactory.getLogger(AreaController.class);

    @ResponseBody//自动转换成json,返回给前端
    @RequestMapping(value="/listarea",method = RequestMethod.GET)
    private Map<String,Object> listArea(){
        logger.info("==========start==============");
        long startTime=System.currentTimeMillis();
        Map<String,Object> modelMap=new HashMap<>();
        List<Area> areaList=new ArrayList<Area>();

        try{
            areaList=areaService.getAreaList();
            modelMap.put("rows",areaList);
            modelMap.put("total",areaList.size());
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        long endTime=System.currentTimeMillis();
        logger.debug("costTime:[{}ms]",endTime-startTime);
        logger.info("==========end==============");
        return modelMap;
    }
}
