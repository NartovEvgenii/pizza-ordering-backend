package com.pizza.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_state")
@Data
public class OrderState {

    @Id
    @Column(name = "id_order_state")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrderState;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @OneToMany(mappedBy = "orderState")
    private List<UserOrder> userOrders = new ArrayList<>();
}
