package com.example.backend.business;

import com.example.backend.entity.Social;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.mapper.SocialMapper;
import com.example.backend.model.SocialDto;
import com.example.backend.service.SocialService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialBusiness {

    @Autowired
    private SocialService socialService;
    @Autowired
    private SocialMapper socialMapper;
    @Autowired
    private UserService userService;

    public SocialDto getSocial() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        Optional<User> userOpt = userService.findById(userId);
        User user = userOpt.get();

        Optional<Social> socialOptional = socialService.findByUser(user);
        Social social = socialOptional.get();

        return socialMapper.toSocial(social);

    }

    public SocialDto createSocial(SocialDto request) throws BaseException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            throw new BaseException("user is empty");
        }
        User user = userOpt.get();

        Social social = socialService.createSocial(user, request);

        return socialMapper.toSocial(social);
    }

    public void deleteSocial() throws BaseException {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            String userId = (String) authentication.getPrincipal();

            Optional<User> userOpt = userService.findById(userId);
            User user = userOpt.get();

            Optional<Social> socialOptional = socialService.findByUser(user);
            Social social = socialOptional.get();

            socialService.deleteSocial(social);
        } catch (Exception e) {
            throw new BaseException("can't delete social");
        }
    }
}
