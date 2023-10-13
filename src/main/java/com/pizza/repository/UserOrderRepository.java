package com.pizza.repository;

import com.pizza.domains.UserOrder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    @Query("select uo from UserOrder uo WHERE uo.mobUser.idUser = :idUser")
    List<UserOrder> findByMobUserId(Long idUser);
}
