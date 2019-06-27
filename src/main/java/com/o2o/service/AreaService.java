package com.o2o.service;

import com.o2o.entity.Area;

import java.util.List;

public interface AreaService {

    @SuppressWarnings("FieldCanBeLocal")
    public final static String AREALISTKEY = "arealist";
    List<Area> getAreaList();
}
