package com.example.backend.api;

import com.example.backend.business.UserBusiness;
import com.example.backend.exception.BaseException;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserBusiness userBusiness;

    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setFood("KFC");
        response.setName("napat");
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<List<RegisterResponseDto>> allUser() {
        List<RegisterResponseDto> response = userBusiness.getAllUser();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profiles")
    public ResponseEntity<ProfileDto> getProfiles() {
        return ResponseEntity.ok(userBusiness.getProfiles());
    }

    @PutMapping("/profiles")
    public ResponseEntity<RegisterResponseDto> updateUserName(@RequestBody UpdateProfileRequest requestDto) throws BaseException {
        RegisterResponseDto response = userBusiness.updateUserName(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profiles/addresses")
    public ResponseEntity<String> createAddress(@RequestBody AddressRequest request) throws BaseException {
        userBusiness.createAddress(request);
        return ResponseEntity.ok("address has created");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto) throws BaseException {
        String response = userBusiness.login(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException {
        return ResponseEntity.ok(userBusiness.refreshToken());
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto requestDto) throws BaseException {
        RegisterResponseDto register = userBusiness.register(requestDto);
        return ResponseEntity.ok(register);
    }

    //upload-file to localhost Api
    @PostMapping
    @RequestMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws BaseException {
        String response = userBusiness.uploadFile(file);
        return ResponseEntity.ok(response);
    }
}


