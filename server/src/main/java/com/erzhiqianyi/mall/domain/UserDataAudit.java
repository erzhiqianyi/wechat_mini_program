package com.erzhiqianyi.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy"},
        allowGetters = true
)
@Data
public class UserDataAudit extends DateAudit{

    @CreatedBy
    @Column(length = 50)
    private String createdBy;

    @LastModifiedBy
    @Column(length = 50)
    private String updatedBy;


}
