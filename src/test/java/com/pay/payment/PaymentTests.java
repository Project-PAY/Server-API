package com.pay.payment;

import com.pay.domain.model.Payment;
import com.pay.domain.repository.AuthRepository;
import com.pay.domain.repository.PaymentRepository;
import com.pay.domain.repository.UserRepository;
import com.pay.domain.service.AuthService;
import com.pay.domain.service.PaymentService;
import com.pay.domain.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    PaymentService paymentService;

    @Test
    @Transactional
    @Rollback(false)
    public void addIncome() {
        paymentService.addIncome(22L, "기타", 100000L);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void addOutcome() {
        paymentService.addOutcome(22L, "식비", 8000L, "체크카드");
    }


    @Test
    public void gets() {
        List<Payment> paymentList = paymentService.gets(22L);

        for (Payment payment : paymentList) {
            System.out.println(payment.toString());
        }
    }

}
