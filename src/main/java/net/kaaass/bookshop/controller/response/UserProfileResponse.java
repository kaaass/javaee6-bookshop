package net.kaaass.bookshop.controller.response;

import lombok.Data;
import net.kaaass.bookshop.dto.UserInfoDto;
import net.kaaass.bookshop.vo.UserOrderCountVo;

@Data
public class UserProfileResponse {

    private UserInfoDto info;

    private UserOrderCountVo orderCount;
}
