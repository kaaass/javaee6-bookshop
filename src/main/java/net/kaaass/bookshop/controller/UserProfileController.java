package net.kaaass.bookshop.controller;

import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.val;
import net.kaaass.bookshop.controller.request.UserAddressRequest;
import net.kaaass.bookshop.controller.request.UserInfoModifyRequest;
import net.kaaass.bookshop.controller.response.UserProfileResponse;
import net.kaaass.bookshop.dao.entity.UserAddressEntity;
import net.kaaass.bookshop.dao.repository.UserAddressRepository;
import net.kaaass.bookshop.dao.repository.UserInfoRepository;
import net.kaaass.bookshop.dto.UserAddressDto;
import net.kaaass.bookshop.dto.UserInfoDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.EntityCreator;
import net.kaaass.bookshop.mapper.PojoMapper;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityIdentity;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.OrderService;
import net.kaaass.bookshop.service.UserService;
import net.kaaass.bookshop.service.metadata.ResourceManager;
import net.kaaass.bookshop.util.TimeUtils;

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

    @Inject
    private UserAddressRepository addressRepository;

    @Inject
    private UserInfoRepository userInfoRepository;

    @Inject
    private UserService userService;

    @Inject
    private ResourceManager resourceManager;

    @Inject
    private OrderService orderService;

    @Inject
    private PojoMapper pojoMapper;

    @Inject
    private EntityCreator entityCreator;

    @GET
    @Path("/")
    @Secured(SecurityRole.USER)
    public UserProfileResponse getUserProfile() throws NotFoundException {
        val result = new UserProfileResponse();
        val auth = userService.getAuthEntityById(getUid(identity));
        val info = pojoMapper.entityToDto(userInfoRepository.findByAuth(auth));
        result.setInfo(info);
        result.setOrderCount(orderService.getUserOrderCount(getUid(identity)));
        return result;
    }

    @POST
    @Path("/")
    @Secured(SecurityRole.USER)
    public UserInfoDto modifyUserProfile(UserInfoModifyRequest request) throws BadRequestException, NotFoundException {
        validateBean(validator, request);
        val auth = userService.getAuthEntityById(getUid(identity));
        val entity = userInfoRepository.findByAuth(auth);
        entity.setAuth(auth);
        val avatar = resourceManager.getEntity(request.getAvatar())
                .orElseThrow(BaseException.supplier(BadRequestException.class, "头像资源不存在！"));
        entity.setAvatar(avatar);
        entity.setWechat(request.getWechat());
        entity.setLastUpdateTime(TimeUtils.nowTimestamp());
        val result = userInfoRepository.save(entity);
        return pojoMapper.entityToDto(result);
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
                        return pojoMapper.entityToDto(userAddressEntity);
                    }
                })
                .collect(Collectors.<UserAddressDto>toList());
    }

    @GET
    @Path("/address/{id}/")
    @Secured(SecurityRole.USER)
    public UserAddressDto getAddressDtoById(@PathParam("id") String id) throws NotFoundException {
        return pojoMapper.entityToDto(getAddressById(id));
    }

    @PUT
    @Path("/address/")
    @Secured(SecurityRole.USER)
    public UserAddressDto addUserAddress(UserAddressRequest request) throws BadRequestException, NotFoundException {
        validateBean(validator, request);
        val entity = entityCreator.createUserAddressEntity(request);
        val auth = userService.getAuthEntityById(getUid(identity));
        entity.setUser(auth);
        entity.setLastUpdateTime(TimeUtils.nowTimestamp());
        val result = addressRepository.save(entity);
        return pojoMapper.entityToDto(result);
    }

    @PUT
    @Path("/address/{id}/")
    @Secured(SecurityRole.USER)
    public UserAddressDto editUserAddress(@PathParam("id") String id, UserAddressRequest request) throws NotFoundException {
        val oldEntity = getAddressById(id);
        val entity = entityCreator.createUserAddressEntity(request);
        val auth = userService.getAuthEntityById(getUid(identity));
        entity.setId(id);
        entity.setUser(auth);
        entity.setLastUpdateTime(TimeUtils.nowTimestamp());
        val result = addressRepository.save(entity);
        return pojoMapper.entityToDto(result);
    }

    @DELETE
    @Path("/address/{id}/")
    @Secured(SecurityRole.USER)
    public boolean removeUserAddress(@PathParam("id") String id) throws NotFoundException {
        val entity = getAddressById(id);
        addressRepository.delete(entity);
        return true;
    }

    @POST
    @Path("/address/{id}/default/")
    @Secured(SecurityRole.USER)
    public boolean setUserDefaultAddress(@PathParam("id") String id) throws NotFoundException {
        val entity = getAddressById(id);
        // 设置其他为非默认、当前为默认
        val auth = entity.getUser();
        for (val addr : auth.getAddresses()) {
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
