package com.pay.service;

import com.pay.domain.PaymentDomain;
import com.pay.domain.UserDomain;
import com.pay.repository.PaymentRepository;
import com.pay.repository.UserRepository;
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
            paymentRepository.save(PaymentDomain.builder()
                    .user(
                            userRepository.save(UserDomain.builder()
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
