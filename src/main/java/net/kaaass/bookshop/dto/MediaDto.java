package net.kaaass.bookshop.dto;

import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class MediaDto {
    private String id;

    private String type;

    private String url;

    private String uploaderUid;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date uploadTime;

    public MediaDto() {
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUploaderUid() {
        return this.uploaderUid;
    }

    public Date getUploadTime() {
        return this.uploadTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUploaderUid(String uploaderUid) {
        this.uploaderUid = uploaderUid;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MediaDto)) return false;
        final MediaDto other = (MediaDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$uploaderUid = this.getUploaderUid();
        final Object other$uploaderUid = other.getUploaderUid();
        if (this$uploaderUid == null ? other$uploaderUid != null : !this$uploaderUid.equals(other$uploaderUid))
            return false;
        final Object this$uploadTime = this.getUploadTime();
        final Object other$uploadTime = other.getUploadTime();
        if (this$uploadTime == null ? other$uploadTime != null : !this$uploadTime.equals(other$uploadTime))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MediaDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $uploaderUid = this.getUploaderUid();
        result = result * PRIME + ($uploaderUid == null ? 43 : $uploaderUid.hashCode());
        final Object $uploadTime = this.getUploadTime();
        result = result * PRIME + ($uploadTime == null ? 43 : $uploadTime.hashCode());
        return result;
    }

    public String toString() {
        return "MediaDto(id=" + this.getId() + ", type=" + this.getType() + ", url=" + this.getUrl() + ", uploaderUid=" + this.getUploaderUid() + ", uploadTime=" + this.getUploadTime() + ")";
    }
}
