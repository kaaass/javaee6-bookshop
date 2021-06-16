package net.kaaass.bookshop.controller;

import java8.util.function.Function;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.var;
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
import net.kaaass.bookshop.mapper.UserMapper;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityIdentity;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.service.OrderService;
import net.kaaass.bookshop.service.UserService;
import net.kaaass.bookshop.service.metadata.ResourceManager;
import net.kaaass.bookshop.util.TimeUtils;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    private UserMapper userMapper;

    @GET
    @Path("/")
    @Secured(SecurityRole.USER)
    public UserProfileResponse getUserProfile() throws NotFoundException {
        var result = new UserProfileResponse();
        var auth = userService.getAuthEntityById(getUid(identity));
        var info = userMapper.userInfoEntityToDto(userInfoRepository.findByAuth(auth));
        result.setInfo(info);
        result.setOrderCount(orderService.getUserOrderCount(getUid(identity)));
        return result;
    }

    @POST
    @Path("/")
    @Secured(SecurityRole.USER)
    public UserInfoDto modifyUserProfile(UserInfoModifyRequest request) throws BadRequestException, NotFoundException {
        validateBean(validator, request);
        var auth = userService.getAuthEntityById(getUid(identity));
        var entity = userInfoRepository.findByAuth(auth);
        entity.setAuth(auth);
        var avatar = resourceManager.getEntity(request.getAvatar())
                        .orElseThrow(BaseException.supplier(BadRequestException.class, "头像资源不存在！"));
        entity.setAvatar(avatar);
        entity.setWechat(request.getWechat());
        entity.setLastUpdateTime(TimeUtils.nowTimestamp());
        var result = userInfoRepository.save(entity);
        return userMapper.userInfoEntityToDto(result);
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
        return addressRepository.findById(id)
                .filter(new Predicate<UserAddressEntity>() {
                    @Override
                    public boolean test(UserAddressEntity addressEntity) {
                        return addressEntity.getUid().equals(getUid(identity));
                    }
                }) // 本人收货地址
                .map(new Function<UserAddressEntity, UserAddressDto>() {
                    @Override
                    public UserAddressDto apply(UserAddressEntity userAddressEntity) {
                        return userMapper.userAddressEntityToDto(userAddressEntity);
                    }
                })
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此收货地址！"));
    }

    @POST
    @Path("/address/")
    @Secured(SecurityRole.USER)
    public UserAddressDto addUserAddress(UserAddressDto userAddressDto) throws BadRequestException {
        validateBean(validator, userAddressDto);
        var entity = userMapper.userAddressDtoToEntity(userAddressDto);
        entity.setUid(getUid(identity));
        entity.setLastUpdateTime(TimeUtils.nowTimestamp());
        var result = addressRepository.save(entity);
        return userMapper.userAddressEntityToDto(result);
    }
}
