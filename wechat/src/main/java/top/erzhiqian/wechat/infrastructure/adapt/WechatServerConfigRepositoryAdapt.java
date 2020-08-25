package top.erzhiqian.wechat.infrastructure.adapt;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.erzhiqian.wechat.authentication.domain.entity.AppConfig;
import top.erzhiqian.wechat.authentication.domain.repository.AppConfigRepository;
import top.erzhiqian.wechat.infrastructure.convert.AppServerConfigConvert;
import top.erzhiqian.wechat.infrastructure.po.WechatAppConfigPO;
import top.erzhiqian.wechat.infrastructure.repository.jdbc.AppServerConfigRepository;
import top.erzhiqian.wechat.scurity.domain.entity.ServerVerifyConfig;
import top.erzhiqian.wechat.scurity.domain.repository.WechatServerConfigRepository;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class WechatServerConfigRepositoryAdapt implements WechatServerConfigRepository, AppConfigRepository {

    private AppServerConfigRepository repository;


    private AppServerConfigConvert configConvert;

    public WechatServerConfigRepositoryAdapt(AppServerConfigRepository repository,
                                             AppServerConfigConvert configConvert) {
        this.repository = repository;
        this.configConvert = configConvert;
    }

    @Override
    public Optional<ServerVerifyConfig> findServerVerifyConfig(String appId) {
        return findByAppId(appId)
                .map(configConvert::convertToServerVerifyEntity);
    }

    @Override
    @Transactional
    public void updateVerifyStatus(Long id, String status) {
        if (null == id || StringUtils.isEmpty(status)) {
            return;
        }
        repository.updateVerifyStatus(id, status);
    }

    @Override
    public Optional<AppConfig> findAppConfig(String appId) {
        return findByAppId(appId)
                .map(configConvert::convertToAppConfigEntity);
    }

    private Optional<WechatAppConfigPO> findByAppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            return Optional.empty();
        }
        return repository.findByAppId(appId);
    }
}
