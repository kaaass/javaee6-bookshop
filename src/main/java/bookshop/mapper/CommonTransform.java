package bookshop.mapper;

import bookshop.dao.entity.MediaEntity;
import bookshop.dao.entity.UserAuthEntity;
import bookshop.dao.entity.UserInfoEntity;

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
