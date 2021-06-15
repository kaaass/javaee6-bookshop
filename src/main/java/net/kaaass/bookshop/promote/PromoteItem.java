package net.kaaass.bookshop.promote;

import lombok.Data;
import net.kaaass.bookshop.dto.ProductDto;

@Data
public class PromoteItem {

    private ProductDto product;

    private float price;

    private int count;
}
