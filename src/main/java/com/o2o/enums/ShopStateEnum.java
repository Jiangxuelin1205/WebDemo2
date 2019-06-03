package com.o2o.enums;

public enum ShopStateEnum {

    CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(-1001, "系统内部错误"), NULL_SHOPID(-1002, "ShopId为空"), NULL_SHOP(-1003, "Shop信息为空");

    private int state;
    private String stateInfo;

    ShopStateEnum(int state, String stateInfo) {
        this.state=state;
        this.stateInfo=stateInfo;
    }

    /**
     * 传入state,返回相应的Enum的值
     */
    public ShopStateEnum stateOf(int state){
        for(ShopStateEnum e:ShopStateEnum.values()){
            if(e.state==state){
                return e;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
