package net.kaaass.bookshop.controller.page;

import lombok.Data;
import net.kaaass.bookshop.dao.Pageable;

import javax.faces.bean.RequestScoped;

@Data
@RequestScoped
public class PageInfo {

    private Pageable pageable;
}
