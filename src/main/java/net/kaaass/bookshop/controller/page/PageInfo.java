package net.kaaass.bookshop.controller.page;

import lombok.Data;
import net.kaaass.bookshop.dao.Pageable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Data
@SessionScoped
@Named("pageInfo")
public class PageInfo implements Serializable {

    private Pageable pageable;
}
