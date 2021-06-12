package net.kaaass.bookshop.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.kaaass.bookshop.vo.AuthTokenVo;

@Data
@AllArgsConstructor
public class LoginResponse {

    private AuthTokenVo authToken;

    private String phone;

    private boolean admin;
}
