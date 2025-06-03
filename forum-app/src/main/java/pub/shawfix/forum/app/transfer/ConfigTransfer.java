package pub.shawfix.forum.app.transfer;


import pub.shawfix.forum.api.request.config.ConfigAddRequest;
import pub.shawfix.forum.api.request.config.ConfigPageRequest;
import pub.shawfix.forum.api.request.config.ConfigUpdateRequest;
import pub.shawfix.forum.api.response.config.ConfigResponse;
import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.enums.ConfigTypeEn;
import pub.shawfix.forum.common.support.SafesUtil;
import pub.shawfix.forum.domain.entity.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shawfix
 * @create 2025/6/3 10:01
 * @desc
 **/
public class ConfigTransfer {
    public static Config toConfig(ConfigPageRequest request) {
        return Config.builder()
                .state(AuditStateEn.getEntity(request.getState()))
                .name(request.getName())
                .type(ConfigTypeEn.getEntity(request.getType()))
                .build();
    }

    public static List<ConfigResponse> toConfigResponses(List<Config> configList) {
        List<ConfigResponse> responses = new ArrayList<>();

        SafesUtil.ofList(configList).forEach(config -> responses.add(ConfigResponse.builder()
                .id(config.getId())
                .createAt(config.getCreateAt())
                .updateAt(config.getUpdateAt())
                .content(config.getContent())
                .creator(config.getCreator())
                .endAt(config.getEndAt())
                .name(config.getName())
                .startAt(config.getStartAt())
                .state(config.getState().getDesc())
                .type(config.getType().getDesc())
                .build()));

        return responses;
    }

    public static Config toConfig(ConfigAddRequest request) {
        return Config.builder()
                .content(request.getContent())
                .endAt(request.getEndAt())
                .name(request.getName())
                .startAt(request.getStartAt())
                .state(AuditStateEn.WAIT)
                .type(ConfigTypeEn.getEntity(request.getType()))
                .build();
    }

    public static void update(Config config, ConfigUpdateRequest request) {
        config.setContent(request.getContent());
        config.setEndAt(request.getEndAt());
        config.setName(request.getName());
        config.setStartAt(request.getStartAt());
        config.setType(ConfigTypeEn.getEntity(request.getType()));
        config.setState(AuditStateEn.WAIT);
    }
}
