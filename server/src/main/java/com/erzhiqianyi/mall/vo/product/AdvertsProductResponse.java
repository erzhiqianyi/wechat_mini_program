package com.erzhiqianyi.mall.vo.product;

import com.erzhiqianyi.mall.domain.product.AdvertsProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;

@Data
public class AdvertsProductResponse {
    private String advertUrl;

    private String id;

    private String picUrl;

    private String title;

    public AdvertsProductResponse(AdvertsProduct product) {

    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant createAt;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant updateAt;


}
