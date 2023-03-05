package com.pizza.repository;

import com.pizza.domains.MobUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MobUser, Long> {
    MobUser findByEmail(String email);
}
