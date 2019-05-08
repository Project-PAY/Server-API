package com.pay.web.controller;

import com.pay.domain.model.Payment;
import com.pay.domain.service.AuthService;
import com.pay.domain.service.PaymentService;
import com.pay.web.request.IncomeRequest;
import com.pay.web.request.OutcomeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    AuthService authService;
    @Autowired
    PaymentService paymentService;


    @PostMapping("/income")
    public void addIncome(@RequestBody IncomeRequest request,
                          @RequestHeader("PAY-AUTH-TOKEN") String token) {
        paymentService.addIncome(authService.getUserIndex(token), request.getContent(), request.getPrice());
    }


    @PostMapping("/outcome")
    public void addOutcome(@RequestBody OutcomeRequest request,
                           @RequestHeader("PAY-AUTH-TOKEN") String token) {
        paymentService.addOutcome(authService.getUserIndex(token), request.getContent(), request.getPrice(), request.getMethod());
    }


    @GetMapping("/payments")
    public ResponseEntity gets(@RequestHeader("PAY-AUTH-TOKEN") String token) {
        List<Payment> paymentList = paymentService.gets(22L);

        for (Payment payment : paymentList) {
            System.out.println(payment.toString());
        }

        return ResponseEntity.ok(paymentService.gets(authService.getUserIndex(token)));
    }

}
