package com.example.backend.service;

import com.example.backend.entity.Social;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.model.SocialDto;
import com.example.backend.repository.SocialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialService {
    @Autowired
    private SocialRepo socialRepo;


    public Optional<Social> findByUser(User user) {
        return socialRepo.findByUser(user);
    }

    public Optional<Social> findById(String userId) {
        return socialRepo.findById(userId);
    }

    public Social createSocial(User user, SocialDto request) throws BaseException {
        try {
            Social social = new Social();

            social.setUser(user);
            social.setFacebook(request.getFacebook());
            social.setInstagram(request.getInstagram());
            social.setLine(request.getLine());
            social.setTiktok(request.getTiktok());

            return socialRepo.save(social);
        } catch (Exception e) {
            throw new BaseException("can't create social");
        }

    }

    public void deleteSocial(Social social) {
        socialRepo.delete(social);
    }
}
