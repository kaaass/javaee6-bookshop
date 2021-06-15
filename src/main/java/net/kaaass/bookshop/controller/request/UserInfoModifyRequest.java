package net.kaaass.bookshop.controller.request;

import lombok.Data;
import net.kaaass.bookshop.constraints.Uuid;

import javax.validation.constraints.Size;

@Data
public class UserInfoModifyRequest {

    @Size(
            message = "wechat格式错误",
            min = 1,
            max = 50
    )
    private String wechat;

    @Uuid(message = "avatar格式错误")
    private String avatar;
}
