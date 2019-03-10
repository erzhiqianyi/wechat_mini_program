package com.erzhiqianyi.mall.domain.product;

import com.erzhiqianyi.mall.domain.DateAudit;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "adverts_product")
public class AdvertsProduct extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String advertUrl;

    private String picUrl;

    private String title;



}
