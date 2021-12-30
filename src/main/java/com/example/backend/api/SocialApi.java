package com.example.backend.api;

import com.example.backend.business.SocialBusiness;
import com.example.backend.exception.BaseException;
import com.example.backend.model.SocialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/social")
public class SocialApi {

    @Autowired
    private SocialBusiness socialBusiness;

    @GetMapping
    private ResponseEntity<SocialDto> getSocial() {
        return ResponseEntity.ok(socialBusiness.getSocial());
    }

    @PostMapping
    private ResponseEntity<SocialDto> createSocial(@RequestBody SocialDto request) throws BaseException {
        return ResponseEntity.ok(socialBusiness.createSocial(request));
    }

    @DeleteMapping
    private ResponseEntity<String> deleteSocial() throws BaseException {
        socialBusiness.deleteSocial();
        return ResponseEntity.ok("delete complete");
    }
}
