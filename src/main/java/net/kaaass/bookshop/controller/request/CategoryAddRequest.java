package net.kaaass.bookshop.controller.request;

import lombok.Data;

/**
 * 分类添加请求
 * @author kaaass
 */
@Data
public class CategoryAddRequest {

    private String name;

    private String parentId;
}
