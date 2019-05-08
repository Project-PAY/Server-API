package com.pay.web.controller;

import com.pay.domain.service.UserService;
import com.pay.web.request.UserRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/users")
    @ApiOperation(value = "Join")
    public void join(@RequestBody UserRequest request) {
        userService.join(
                request.getIdentify(),
                request.getPassword(),
                request.getName(),
                request.getFixedIncome(),
                request.getCycleIncome(),
                request.getCurrentMoney()
        );
    }

}
