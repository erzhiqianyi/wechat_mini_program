package top.erzhiqian.wechat.core.infrastructure.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;


@MappedSuperclass
@Data
public class BasePO {

    protected Instant createAt;

    @Column(columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ")
    protected Instant lastModified;

}
