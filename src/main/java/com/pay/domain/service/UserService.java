package com.pay.domain.service;

import com.pay.domain.model.Payment;
import com.pay.domain.model.User;
import com.pay.domain.repository.PaymentRepository;
import com.pay.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    @Transactional
    public void join(String identify,
                     String password,
                     String name,
                     Long fixedIncome,
                     String cycleIncome,
                     Long currentMoney) {

        try {
            paymentRepository.save(Payment.builder()
                    .user(
                            userRepository.save(User.builder()
                                    .identify(identify)
                                    .password(password)
                                    .name(name)
                                    .capital(currentMoney)
                                    .income(currentMoney)
                                    .fixedIncome(fixedIncome)
                                    .cycleIncome(cycleIncome)
                                    .build()
                            )
                    )
                    .kind("수입")
                    .content("소지금")
                    .price(currentMoney)
                    .build()
            );
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
