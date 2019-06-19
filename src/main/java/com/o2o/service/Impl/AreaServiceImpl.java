package com.o2o.service.Impl;

import com.o2o.dao.AreaDao;
import com.o2o.entity.Area;
import com.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    @Transactional
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
