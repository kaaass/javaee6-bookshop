package bookshop.service;

import bookshop.controller.request.CartAddRequest;
import bookshop.dto.CartDto;
import bookshop.exception.BadRequestException;
import bookshop.exception.ForbiddenException;
import bookshop.exception.NotFoundException;

import javax.ejb.Local;
import java.util.List;

/**
 * 购物车服务
 *
 * @author kaaass
 */
@Local
public interface CartService {

    CartDto getById(String id) throws NotFoundException;

    CartDto addToCart(CartAddRequest request) throws NotFoundException, BadRequestException;

    void removeFromCart(String id) throws NotFoundException, ForbiddenException;

    CartDto modifyItemCount(String id, int count) throws NotFoundException, ForbiddenException, BadRequestException;

    List<CartDto> getAllPerUser();

    void deleteById(String id);
}
