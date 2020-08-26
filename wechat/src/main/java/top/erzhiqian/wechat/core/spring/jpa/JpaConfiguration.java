package top.erzhiqian.wechat.core.spring.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "top.erzhiqian.wechat.infrastructure.repository.jdbc")
@Configuration
public class JpaConfiguration {
}
