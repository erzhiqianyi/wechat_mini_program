package top.erzhiqian.wechat.infrastructure.adapt;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.erzhiqian.wechat.scurity.domain.entity.ServerVerifyConfig;
import top.erzhiqian.wechat.scurity.domain.repository.WechatServerConfigRepository;
import top.erzhiqian.wechat.infrastructure.convert.AppServerConfigConvert;
import top.erzhiqian.wechat.infrastructure.po.WechatAppConfigPO;
import top.erzhiqian.wechat.infrastructure.repository.jdbc.AppServerConfigRepository;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class WechatServerConfigRepositoryAdapt implements WechatServerConfigRepository {

    private AppServerConfigRepository repository;


    private AppServerConfigConvert configConvert;

    public WechatServerConfigRepositoryAdapt(AppServerConfigRepository repository,
                                             AppServerConfigConvert configConvert) {
        this.repository = repository;
        this.configConvert = configConvert;
    }

    @Override
    public Optional<ServerVerifyConfig> findServerVerifyConfig(String appId) {
        if (StringUtils.isEmpty(appId)) {
            return Optional.empty();
        }
        Optional<WechatAppConfigPO> configOptional = repository.findByAppId(appId);
        return configOptional.map(configConvert::convertToEntity);
    }

    @Override
    @Transactional
    public void updateVerifyStatus(Long id, String status) {
        if (null == id || StringUtils.isEmpty(status)) {
            return;
        }
        repository.updateVerifyStatus(id, status);
    }
}
