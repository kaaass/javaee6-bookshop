package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.MediaEntity;
import net.kaaass.bookshop.dao.entity.UserAuthEntity;
import net.kaaass.bookshop.dao.entity.UserInfoEntity;

public class CommonTransform {

    public String getAvatarFromAuth(UserAuthEntity auth) {
        final UserInfoEntity info = auth.getUserInfo();
        if (info == null) {
            return "";
        }
        final MediaEntity avatar = info.getAvatar();
        if (avatar == null) {
            return "";
        }
        return avatar.getUrl();
    }
}
