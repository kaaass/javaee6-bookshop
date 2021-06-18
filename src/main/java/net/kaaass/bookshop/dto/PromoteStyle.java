package net.kaaass.bookshop.dto;

import lombok.Getter;

/**
 * 打折样式
 *
 * @author kaaass
 */
public enum PromoteStyle {

    INFO("badge-success"),

    HOT("badge-danger"),

    GREAT("badge-warning"),

    NORMAL("badge-info");

    @Getter
    private final String style;

    PromoteStyle(String style) {
        this.style = style;
    }

}
