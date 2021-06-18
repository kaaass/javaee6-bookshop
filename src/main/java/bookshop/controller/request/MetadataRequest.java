package bookshop.controller.request;

import javax.validation.constraints.Size;

public class MetadataRequest {

    @Size(
            message = "键长度必须为1~50",
            min = 1,
            max = 50
    )
    private String key;

    private String value;

    public MetadataRequest() {
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MetadataRequest)) return false;
        final MetadataRequest other = (MetadataRequest) o;
        if (!other.canEqual(this)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        return this$value == null ? other$value == null : this$value.equals(other$value);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MetadataRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String toString() {
        return "MetadataRequest(key=" + this.getKey() + ", value=" + this.getValue() + ")";
    }
}
