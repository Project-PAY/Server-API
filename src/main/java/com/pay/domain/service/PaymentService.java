package com.pay.domain.service;

import com.pay.domain.model.Payment;
import com.pay.domain.model.User;
import com.pay.domain.repository.PaymentRepository;
import com.pay.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void addIncome(Long index,
                          String content,
                          Long price) {
        User user = userRepository.getOne(index);
        user.setCapital(user.getCapital() + price);
        user.setIncome(user.getIncome() + price);

        paymentRepository.save(Payment.builder()
                .kind("수입")
                .user(userRepository.save(user))
                .content(content)
                .price(price)
                .build()
        );
    }

    @Transactional
    public void addOutcome(Long index,
                           String content,
                           Long price,
                           String method) {
        User user = userRepository.getOne(index);
        user.setCapital(user.getCapital() - price);
        user.setOutcome(user.getOutcome() - price);

        paymentRepository.save(Payment.builder()
                .kind("지출")
                .user(userRepository.save(user))
                .content(content)
                .price(-price)
                .method(method)
                .build()
        );
    }


    @Transactional
    public List gets(Long index) {
        return paymentRepository.findAllByUser_Index(index);
    }

}
