//package com.example.webdogiadung.rest;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import web.examination.CostumeForRent.dto.request.LoginRequest;
//
//@RestController
//public class TestController {
//
//    @PostMapping(value = "test",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String test(LoginRequest login, @RequestPart("file") MultipartFile file) {
//        System.out.println(login);
//        return "test";
//    }
//}
