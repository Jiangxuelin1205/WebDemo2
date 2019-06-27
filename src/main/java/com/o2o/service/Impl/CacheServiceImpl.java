package com.o2o.service.Impl;

import com.o2o.cache.JedisPoolWriper;
import com.o2o.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Set;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private JedisPoolWriper jedisPoolWriper;

    @Override
    public void removeFromCache(String keyPrefix) {
        Jedis sjedis = jedisPoolWriper.getJedisPool().getResource();
        Set<String> keySet = sjedis.keys(keyPrefix + "*");
        for (String key : keySet) {
            sjedis.del(key);
        }
    }
}
