package com.pay.domain.repository;

import com.pay.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdentifyAndPasswordAndAvailable(String identify, String password, String available);

}
