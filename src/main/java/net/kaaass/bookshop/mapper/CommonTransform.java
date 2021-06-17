package net.kaaass.bookshop.mapper;

import lombok.val;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;

public class CommonTransform {

    public String getAvatarFromAuth(UserAuthEntity auth) {
        val info = auth.getUserInfo();
        if (info == null) {
            return "";
        }
        val avatar = info.getAvatar();
        if (avatar == null) {
            return "";
        }
        return avatar.getUrl();
    }
}
