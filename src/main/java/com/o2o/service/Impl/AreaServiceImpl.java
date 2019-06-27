package com.o2o.service.Impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.o2o.cache.JedisUtil;
import com.o2o.dao.AreaDao;
import com.o2o.entity.Area;
import com.o2o.exception.AreaOperationException;
import com.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    /*@Autowired
    private AreaDao areaDao;

    @Override
    @Transactional
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }*/
    @Autowired
    private AreaDao areaDao;

    private JedisPool jedisPool;
   /* @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;*/

    private String AREALISTKEY = "arealist";

    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Override
    @Transactional
    public List<Area> getAreaList() {
        // 定义redis的key
        String key = AREALISTKEY;
        // 定义接收对象
        List<Area> areaList = null;
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        // 判断key是否存在

        Jedis sjedis = jedisPool.getResource();
        boolean exists = sjedis.exists(key);
        sjedis.close();
        if(!exists){
            areaList = areaDao.queryArea();
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
            sjedis.set(SafeEncoder.encode(key),SafeEncoder.encode(jsonString));
        }else {
            // 若存在，则直接从redis里面取出相应数据
//            String jsonString = jedisStrings.get(key);
            String jsonString = sjedis.get(key);
            sjedis.close();
            // 指定要将string转换成的集合类型
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                // 将相关key对应的value里的的string转换成对象的实体类集合
                areaList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}
