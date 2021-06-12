package net.kaaass.bookshop.controller.request;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * 账户注册请求
 * @author kaaass
 */
@Data
public class RegisterRequest {
    /**
     * 注册手机号
     */
    @Pattern(
            message = "手机号格式错误",
            regexp = "^\\d{11}$"
    )
    private String phone;

    /**
     * 密码
     */
    @Pattern(
            message = "密码应至少包含大写、小写、数字，长度6~20",
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,20}$"
    )
    private String password;
}
