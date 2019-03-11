package com.erzhiqianyi.mall.domain.product;

import com.erzhiqianyi.mall.domain.DateAudit;
import com.erzhiqianyi.mall.domain.UserDataAudit;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "adverts_product")
public class AdvertsProduct extends UserDataAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String advertUrl;

    private String picUrl;

    private String title;

    private int sort;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

}
