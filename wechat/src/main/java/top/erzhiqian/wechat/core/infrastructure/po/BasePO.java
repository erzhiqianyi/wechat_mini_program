package top.erzhiqian.wechat.core.infrastructure.po;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;


@MappedSuperclass
@Data
public class BasePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Instant createAt;

    @Column(columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ")
    protected Instant lastModified;

}
