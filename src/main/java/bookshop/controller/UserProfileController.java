package bookshop.controller;

import bookshop.controller.request.UserAddressRequest;
import bookshop.controller.response.UserProfileResponse;
import bookshop.dao.entity.UserAddressEntity;
import bookshop.dao.entity.UserAuthEntity;
import bookshop.dao.repository.UserAddressRepository;
import bookshop.dto.UserAddressDto;
import bookshop.exception.BadRequestException;
import bookshop.exception.BaseException;
import bookshop.exception.NotFoundException;
import bookshop.mapper.UserMapper;
import bookshop.security.Secured;
import bookshop.security.SecurityIdentity;
import bookshop.security.SecurityRole;
import bookshop.service.OrderService;
import bookshop.service.UserService;
import bookshop.util.TimeUtils;
import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/user/profile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserProfileController extends BaseController {

    @Inject
    private Validator validator;

    @Inject
    private SecurityIdentity identity;

    @EJB
    private UserAddressRepository addressRepository;

    @EJB
    private UserService userService;

    @EJB
    private OrderService orderService;

    @Inject
    private UserMapper userMapper;

    @GET
    @Path("/")
    @Secured(SecurityRole.USER)
    public UserProfileResponse getUserProfile() throws NotFoundException {
        UserProfileResponse result = new UserProfileResponse();
        result.setOrderCount(orderService.getUserOrderCount(getUid(identity)));
        return result;
    }

    /*
     * 收货地址相关
     */

    @GET
    @Path("/address/")
    @Secured(SecurityRole.USER)
    public List<UserAddressDto> getAllAddresses() throws NotFoundException {
        return StreamSupport.stream(addressRepository.findAllByUid(getUid(identity)))
                .map(new Function<UserAddressEntity, UserAddressDto>() {
                    @Override
                    public UserAddressDto apply(UserAddressEntity userAddressEntity) {
                        return userMapper.userAddressEntityToDto(userAddressEntity);
                    }
                })
                .collect(Collectors.<UserAddressDto>toList());
    }

    @GET
    @Path("/address/{id}/")
    @Secured(SecurityRole.USER)
    public UserAddressDto getAddressDtoById(@PathParam("id") String id) throws NotFoundException {
        return userMapper.userAddressEntityToDto(getAddressById(id));
    }

    @PUT
    @Path("/address/")
    @Secured(SecurityRole.USER)
    public UserAddressDto addUserAddress(UserAddressRequest request) throws BadRequestException, NotFoundException {
        validateBean(validator, request);
        UserAddressEntity entity = userMapper.userAddressRequestToEntity(request);
        UserAuthEntity auth = userService.getAuthEntityById(getUid(identity));
        entity.setUser(auth);
        entity.setLastUpdateTime(TimeUtils.nowTimestamp());
        UserAddressEntity result = addressRepository.save(entity);
        return userMapper.userAddressEntityToDto(result);
    }

    @PUT
    @Path("/address/{id}/")
    @Secured(SecurityRole.USER)
    public UserAddressDto editUserAddress(@PathParam("id") String id, UserAddressRequest request) throws NotFoundException {
        UserAddressEntity oldEntity = getAddressById(id);
        UserAddressEntity entity = userMapper.userAddressRequestToEntity(request);
        UserAuthEntity auth = userService.getAuthEntityById(getUid(identity));
        entity.setId(id);
        entity.setUser(auth);
        entity.setLastUpdateTime(TimeUtils.nowTimestamp());
        UserAddressEntity result = addressRepository.save(entity);
        return userMapper.userAddressEntityToDto(result);
    }

    @DELETE
    @Path("/address/{id}/")
    @Secured(SecurityRole.USER)
    public boolean removeUserAddress(@PathParam("id") String id) throws NotFoundException {
        UserAddressEntity entity = getAddressById(id);
        addressRepository.delete(entity);
        return true;
    }

    @POST
    @Path("/address/{id}/default/")
    @Secured(SecurityRole.USER)
    public boolean setUserDefaultAddress(@PathParam("id") String id) throws NotFoundException {
        UserAddressEntity entity = getAddressById(id);
        // 设置其他为非默认、当前为默认
        UserAuthEntity auth = entity.getUser();
        for (final UserAddressEntity addr : auth.getAddresses()) {
            addr.setDefaultAddress(addr.getId().equals(id));
            addressRepository.save(addr);
        }
        return true;
    }

    private UserAddressEntity getAddressById(String id) throws NotFoundException {
        return addressRepository.findById(id)
                .filter(new Predicate<UserAddressEntity>() {
                    @Override
                    public boolean test(UserAddressEntity addressEntity) {
                        return addressEntity.getUser().getId().equals(getUid(identity));
                    }
                }) // 本人收货地址
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此收货地址！"));
    }
}
