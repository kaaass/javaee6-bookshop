package net.kaaass.bookshop.promote;

import lombok.Getter;
import net.kaaass.bookshop.service.CategoryService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Getter
public class ServiceAdapter {

    @Inject
    private CategoryService categoryService;
}
