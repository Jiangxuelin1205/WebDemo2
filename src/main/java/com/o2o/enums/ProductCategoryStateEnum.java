package com.o2o.enums;

public enum ProductCategoryStateEnum {

    SUCCESS(1, "成功"), INNER_ERROR(-1001, "操作失败"), EMPTY_LIST(-1002, "添加的店铺少于1");

    private int state;
    private String stateInfo;

     ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
