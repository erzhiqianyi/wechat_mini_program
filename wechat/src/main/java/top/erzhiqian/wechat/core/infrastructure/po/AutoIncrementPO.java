package top.erzhiqian.wechat.core.infrastructure.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Data
public class AutoIncrementPO  extends BasePO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


}
