package top.erzhiqian.wechat.authentication.client.dto;

import lombok.Data;

@Data
public class AuthenticationUserDTO {

    private String token;

    private Boolean needPhone;

}
