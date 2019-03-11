package com.erzhiqianyi.mall.domain.product;

public enum  ProductStatus {
    CREATE("创建"),
    CHECKED("已审核"),
    SALE("上架"),
    OFF("已下架"),
    DELETED("已删除");

    private String remark;

    ProductStatus(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
}
