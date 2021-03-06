package com.example.backend.business;

import com.example.backend.entity.Address;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.mapper.AddressMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.*;
import com.example.backend.service.AddressService;
import com.example.backend.service.FileUploadService;
import com.example.backend.service.TokenService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserBusiness {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressMapper addressMapper;

    public RegisterResponseDto register(RegisterRequestDto requestDto) throws BaseException {
        User user = userService.create(requestDto.getEmail(), requestDto.getPassword(), requestDto.getName());

        return userMapper.toRegisterResponse(user);

    }

    public String getProductId(String id) throws BaseException {
        if ("1234".equalsIgnoreCase(id)) {
            throw new BaseException("id is exist");
        }
        return id;
    }


    public String uploadFile(MultipartFile file) throws BaseException {
        if (file == null) {
            throw new BaseException("file is null");
        }

        if (file.getSize() > 1048576 * 2) {
            throw new BaseException("file is large");
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");

        if (!supportedTypes.contains(file.getContentType())) {
            throw new BaseException("file not support");
        }

        fileUploadService.uploadFile(file);

        return "file has uploaded";
    }

    public String login(LoginRequestDto requestDto) throws BaseException {
        Optional<User> byEmail = userService.findByEmail(requestDto.getEmail());
        if (byEmail.isEmpty()) {
            throw new BaseException("email not found");
        }
        User user = byEmail.get();
        if (!userService.matchPassword(requestDto.getPassword(), user.getPassword())) {
            throw new BaseException("password is not correct");
        }

        return tokenService.token(user);
    }

    public List<RegisterResponseDto> getAllUser() {
        List<User> allUser = userService.getAllUser();

        List<RegisterResponseDto> collect = allUser.stream().map(user -> userMapper.toRegisterResponse(user)).collect(Collectors.toList());
        return collect;
    }

    public RegisterResponseDto updateUserName(UpdateProfileRequest requestDto) throws BaseException {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        Optional<User> byId = userService.findById(userId);
        if (byId.isEmpty()) {
            throw new BaseException("can not found id");
        }

        User user = byId.get();
        user.setName(requestDto.getName());
        user.setGender(requestDto.getGender());
        user.setPhone_number(requestDto.getPhone_number());
        user.setDateOfBirth(requestDto.getDateOfBirth());

        User updateUserName = userService.updateUserName(user);

        return userMapper.toRegisterResponse(updateUserName);

    }

    public String refreshToken() throws BaseException {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            throw new BaseException("token expired");
        }
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            throw new BaseException("token expired");
        }
        String userId = (String) authentication.getPrincipal();

        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            throw new BaseException("id not found");
        }

        return tokenService.token(userOptional.get());

    }

    public ProfileDto getProfiles() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        Optional<User> byId = userService.findById(userId);
        User user = byId.get();

        return userMapper.toProfile(user);
    }

    public AddressDto createAddress(AddressRequest request) throws BaseException {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            String userId = (String) authentication.getPrincipal();

            Optional<User> byId = userService.findById(userId);
            User user = byId.get();

            if (ObjectUtils.isEmpty(request)) {
                throw new BaseException("request is empty");
            }

            Address address = userService.createAddress(user, request);
            return addressMapper.toAddress(address);
        } catch (BaseException e) {
            throw new BaseException("can't create address");
        }


    }

    public AddressDto updateAddress(AddressRequest request) throws BaseException {
        if (ObjectUtils.isEmpty(request.getId())) {
            throw new BaseException("id not found");
        }
        Optional<Address> addressOptional = addressService.findById(request.getId());
        if (addressOptional.isEmpty()) {
            throw new BaseException("address not exist");
        }
        Address address = addressOptional.get();
        if (!request.getAddress().isEmpty()) {
            address.setAddress(request.getAddress());
        }
        if (!request.getCity().isEmpty()) {
            address.setCity(request.getCity());
        }
        if (!request.getZipcode().isEmpty()) {
            address.setZipcode(request.getZipcode());
        }

        Address updateAddress = addressService.updateAddress(address);

        return addressMapper.toAddress(updateAddress);

    }

    public void deleteAddress(AddressRequest request) throws BaseException {
        try {
            if (ObjectUtils.isEmpty(request.getId())) {
                throw new BaseException("id can't empty");
            }
            addressService.deleteAddress(request.getId());
        } catch (BaseException e) {
            throw new BaseException("can't delete address");
        }
    }
}
