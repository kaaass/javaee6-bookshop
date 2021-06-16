package net.kaaass.bookshop.controller.request;

import lombok.Data;
import net.kaaass.bookshop.constraints.Uuid;

import javax.validation.constraints.Size;

/**
 * 分类添加请求
 * @author kaaass
 */
@Data
public class CategoryAddRequest {

    @Size(
            message = "分类名字长度必须大于3小于20",
            min = 3,
            max = 20
    )
    private String name;

    @Uuid(message = "父类格式错误", nullable = true)
    private String parentId;
}
