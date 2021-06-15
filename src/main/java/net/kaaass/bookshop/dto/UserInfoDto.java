package net.kaaass.bookshop.dto;

import lombok.Data;

/**
 * 用户信息 DTO
 * @author kaaass
 */
@Data
public class UserInfoDto {

    private UserAuthDto auth;

    private String wechat;

    private MediaDto avatar;
}
