package bookshop.vo;

import bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class AuthTokenVo {
    String token;

    @JsonSerialize(using = DateToLongSerializer.class)
    Date publishTime;

    public AuthTokenVo(String token, Date publishTime) {
        this.token = token;
        this.publishTime = publishTime;
    }

    public String getToken() {
        return this.token;
    }

    public Date getPublishTime() {
        return this.publishTime;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AuthTokenVo)) return false;
        final AuthTokenVo other = (AuthTokenVo) o;
        if (!other.canEqual(this)) return false;
        final Object this$token = this.getToken();
        final Object other$token = other.getToken();
        if (this$token == null ? other$token != null : !this$token.equals(other$token)) return false;
        final Object this$publishTime = this.getPublishTime();
        final Object other$publishTime = other.getPublishTime();
        return this$publishTime == null ? other$publishTime == null : this$publishTime.equals(other$publishTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AuthTokenVo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $token = this.getToken();
        result = result * PRIME + ($token == null ? 43 : $token.hashCode());
        final Object $publishTime = this.getPublishTime();
        result = result * PRIME + ($publishTime == null ? 43 : $publishTime.hashCode());
        return result;
    }

    public String toString() {
        return "AuthTokenVo(token=" + this.getToken() + ", publishTime=" + this.getPublishTime() + ")";
    }
}
