package com.pay.web.controller;

import com.pay.domain.service.AuthService;
import com.pay.web.request.AuthRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/auth")
    @ApiOperation(value = "Login")
    public ResponseEntity login(@RequestBody AuthRequest request) {
        Object token = authService.auth(request.getIdentify(), request.getPassword());

        if (token == null)
            return ResponseEntity.badRequest().body("[ERROR] Identify & Password");

        return ResponseEntity.ok(token);
    }

}
