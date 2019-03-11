package com.erzhiqianyi.mall.vo.product;

import com.erzhiqianyi.mall.domain.product.AdvertsProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;

@Data
public class AdvertsProductResponse {

    private Long id;

    private String advertUrl;

    private String picUrl;

    private String title;

    private Integer sort;

    public AdvertsProductResponse() {
    }

    public AdvertsProductResponse(AdvertsProduct product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.picUrl = product.getPicUrl();
        this.advertUrl = product.getAdvertUrl();
        this.sort = product.getSort();
        this.createAt = product.getCreateAt();
        this.updateAt = product.getUpdateAt();

    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant createAt;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant updateAt;


}
