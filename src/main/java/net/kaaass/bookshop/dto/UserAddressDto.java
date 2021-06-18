package net.kaaass.bookshop.dto;

import lombok.Data;

/**
 * 用户地址 DTO
 *
 * @author kaaass
 */
@Data
public class UserAddressDto {

    private String id;

    private String area;

    private String detailAddress;

    private String mailCode;

    private String phone;

    private String name;

    private boolean defaultAddress;
}
