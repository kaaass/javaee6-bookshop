package net.kaaass.bookshop.service;

import net.kaaass.bookshop.controller.request.CartAddRequest;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.CartEntity;
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

    /**
     * @deprecated
     */
    CartEntity getEntityById(String id) throws NotFoundException;

    /**
     * @deprecated
     */
    CartEntity getEntityByIdAndCheck(String id, String uid) throws NotFoundException, ForbiddenException;

    CartDto addToCart(String uid, CartAddRequest request) throws NotFoundException, BadRequestException;

    void removeFromCart(String uid, String id) throws NotFoundException, ForbiddenException;

    CartDto modifyItemCount(String uid, String id, int count) throws NotFoundException, ForbiddenException, BadRequestException;

    List<CartDto> getAllByUid(String uid, Pageable pageable);
}
