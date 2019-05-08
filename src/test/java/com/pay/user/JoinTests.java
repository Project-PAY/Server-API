package com.pay.user;

import com.pay.domain.model.Payment;
import com.pay.domain.model.User;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class JoinTests {

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
    public void 회원가입() {
        User user = User.builder()
                .identify("test")
                .password("testPassword")
                .name("김용현")
                .capital(100000L)
                .income(100000L)
                .fixedIncome(null)
                .cycleIncome(null)
                .build();

        userRepository.save(user);

        Payment payment = Payment.builder()
                .user(user)
                .kind("수입")
                .content("소지금")
                .price(100000L)
                .build();

        System.out.println(user.toString());
        System.out.println();
        System.out.println(payment.toString());
    }

}
