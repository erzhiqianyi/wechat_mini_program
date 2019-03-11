package com.erzhiqianyi.mall.payload.product;


import com.erzhiqianyi.mall.domain.product.ProductStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AdvertsProductPayload {

    @NotBlank(message = " advertUrl must not be null")
    @Size(max = 100)
    private String advertUrl;

    @NotBlank(message = " pic must not be null")
    @Size(max = 500)
    private String picUrl;


    @NotBlank(message = " title must not be blank")
    @Size(max = 100)
    private String title;

    private int sort;

    private ProductStatus status;

}
