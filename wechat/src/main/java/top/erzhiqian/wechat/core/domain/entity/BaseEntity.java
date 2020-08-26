package top.erzhiqian.wechat.core.domain.entity;

import java.time.Instant;

public class BaseEntity {
    protected Long id;
    protected Instant createAt;
    protected Instant lastModified;

    public Long id() {
        return id;
    }

    public void identify(Long id, Instant createAt, Instant lastModified) {
        this.id = id;
        this.createAt = createAt;
        this.lastModified = lastModified;
    }
}
