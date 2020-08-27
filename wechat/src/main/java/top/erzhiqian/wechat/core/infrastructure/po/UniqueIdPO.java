package top.erzhiqian.wechat.core.infrastructure.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class UniqueIdPO extends BasePO {

    @Id
    @Column(columnDefinition = "varchar(50) not null comment 'id编号' ", unique = true)
    protected String id;

}
