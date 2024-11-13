package com.jjcomp.aggropingpong.common.controller;

import com.jjcomp.aggropingpong.common.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<ResponseData<?>> home() {
        return new ResponseEntity<>(ResponseData.success(), HttpStatus.OK);
    }

    @GetMapping("/index")
    public ResponseEntity<ResponseData<?>> index() {
        return new ResponseEntity<>(ResponseData.success(), HttpStatus.OK);
    }

}
