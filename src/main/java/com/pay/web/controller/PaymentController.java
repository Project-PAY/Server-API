package com.pay.web.controller;

import com.pay.domain.service.AuthService;
import com.pay.domain.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    AuthService authService;
    @Autowired
    PaymentService paymentService;


    @PostMapping("/income")
    public void addIncome(
            @RequestHeader("PAY-AUTH-TOKEN") String token,
            @RequestParam @NotNull String content,
            @RequestParam @NotNull Long price) {
        paymentService.addIncome(authService.getUserIndex(token), content, price);
    }


    @PostMapping("/outcome")
    public void addOutcome(
            @RequestHeader("PAY-AUTH-TOKEN") String token,
            @RequestParam @NotNull String content,
            @RequestParam @NotNull Long price,
            @RequestParam @NotNull @Size(max = 16) String method) {
        paymentService.addOutcome(authService.getUserIndex(token), content, price, method);
    }


    @GetMapping("/payments")
    public List gets(@RequestHeader("PAY-AUTH-TOKEN") String token) {
        return paymentService.gets(authService.getUserIndex(token));
    }

}
