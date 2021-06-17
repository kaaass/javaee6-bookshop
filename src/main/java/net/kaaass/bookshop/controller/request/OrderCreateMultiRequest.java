package net.kaaass.bookshop.controller.request;

import net.kaaass.bookshop.constraints.Uuid;
import net.kaaass.bookshop.dto.CartDto;

import java.util.List;

/**
 * 创建多商品订单请求
 *
 * @author kaaass
 */
public class OrderCreateMultiRequest extends OrderCreateRequest {

    public final static String TYPE = "MULTI";

    public OrderCreateMultiRequest() {
    }

    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

    public List<CartDto> getCachedCartItems() {
        return this.cachedCartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setCachedCartItems(List<CartDto> cachedCartItems) {
        this.cachedCartItems = cachedCartItems;
    }

    @Override
    public String toString() {
        return "OrderCreateMultiRequest(cartItems=" + this.getCartItems() + ", cachedCartItems=" + this.getCachedCartItems() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderCreateMultiRequest)) return false;
        final OrderCreateMultiRequest other = (OrderCreateMultiRequest) o;
        if (!other.canEqual(this)) return false;
        if (!super.equals(o)) return false;
        final Object this$cartItems = this.getCartItems();
        final Object other$cartItems = other.getCartItems();
        if (this$cartItems == null ? other$cartItems != null : !this$cartItems.equals(other$cartItems)) return false;
        final Object this$cachedCartItems = this.getCachedCartItems();
        final Object other$cachedCartItems = other.getCachedCartItems();
        return this$cachedCartItems == null ? other$cachedCartItems == null : this$cachedCartItems.equals(other$cachedCartItems);
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof OrderCreateMultiRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $cartItems = this.getCartItems();
        result = result * PRIME + ($cartItems == null ? 43 : $cartItems.hashCode());
        final Object $cachedCartItems = this.getCachedCartItems();
        result = result * PRIME + ($cachedCartItems == null ? 43 : $cachedCartItems.hashCode());
        return result;
    }

    public static class CartItem {

        @Uuid(message = "id格式不正确")
        private String id;

        public CartItem() {
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof CartItem)) return false;
            final CartItem other = (CartItem) o;
            if (!other.canEqual(this)) return false;
            final Object this$id = this.getId();
            final Object other$id = other.getId();
            return this$id == null ? other$id == null : this$id.equals(other$id);
        }

        protected boolean canEqual(final Object other) {
            return other instanceof CartItem;
        }

        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $id = this.getId();
            result = result * PRIME + ($id == null ? 43 : $id.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "OrderCreateMultiRequest.CartItem(id=" + this.getId() + ")";
        }
    }

    private List<CartItem> cartItems;

    private List<CartDto> cachedCartItems = null;
}
