package net.kaaass.bookshop.controller.response;

import net.kaaass.bookshop.vo.AuthTokenVo;

public class LoginResponse {

    private AuthTokenVo authToken;

    private String phone;

    private boolean admin;

    public LoginResponse(AuthTokenVo authToken, String phone, boolean admin) {
        this.authToken = authToken;
        this.phone = phone;
        this.admin = admin;
    }

    public AuthTokenVo getAuthToken() {
        return this.authToken;
    }

    public String getPhone() {
        return this.phone;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAuthToken(AuthTokenVo authToken) {
        this.authToken = authToken;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LoginResponse)) return false;
        final LoginResponse other = (LoginResponse) o;
        if (!other.canEqual(this)) return false;
        final Object this$authToken = this.getAuthToken();
        final Object other$authToken = other.getAuthToken();
        if (this$authToken == null ? other$authToken != null : !this$authToken.equals(other$authToken)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        return this.isAdmin() == other.isAdmin();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LoginResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $authToken = this.getAuthToken();
        result = result * PRIME + ($authToken == null ? 43 : $authToken.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        result = result * PRIME + (this.isAdmin() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "LoginResponse(authToken=" + this.getAuthToken() + ", phone=" + this.getPhone() + ", admin=" + this.isAdmin() + ")";
    }
}
