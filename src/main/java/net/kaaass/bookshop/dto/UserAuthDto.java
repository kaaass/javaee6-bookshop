package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.security.SecurityRole;

@Data
public class UserAuthDto {

    private String id;

    private String phone;

    private String password;

    private SecurityRole role;
}
