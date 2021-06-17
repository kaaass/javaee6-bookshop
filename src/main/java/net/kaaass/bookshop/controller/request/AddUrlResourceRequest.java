package net.kaaass.bookshop.controller.request;

public class AddUrlResourceRequest {

    private String url;

    private String type;

    public AddUrlResourceRequest() {
    }

    public String getUrl() {
        return this.url;
    }

    public String getType() {
        return this.type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AddUrlResourceRequest)) return false;
        final AddUrlResourceRequest other = (AddUrlResourceRequest) o;
        if (!other.canEqual(this)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        return this$type == null ? other$type == null : this$type.equals(other$type);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AddUrlResourceRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        return result;
    }

    public String toString() {
        return "AddUrlResourceRequest(url=" + this.getUrl() + ", type=" + this.getType() + ")";
    }
}
