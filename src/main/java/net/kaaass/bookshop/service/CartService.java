package net.kaaass.bookshop.service;

import net.kaaass.bookshop.controller.request.CartAddRequest;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dto.CartDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.ForbiddenException;
import net.kaaass.bookshop.exception.NotFoundException;

import javax.ejb.Local;
import java.util.List;

/**
 * 购物车服务
 * @author kaaass
 */
@Local
public interface CartService {

    CartDto getById(String id) throws NotFoundException;

    CartDto addToCart(CartAddRequest request) throws NotFoundException, BadRequestException;

    void removeFromCart(String id) throws NotFoundException, ForbiddenException;

    CartDto modifyItemCount(String id, int count) throws NotFoundException, ForbiddenException, BadRequestException;

    List<CartDto> getAllPerUser(Pageable pageable);

    void deleteById(String id);
}
