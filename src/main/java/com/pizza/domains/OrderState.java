package com.pizza.domains;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_state")
@Data
@NoArgsConstructor
public class OrderState {

    @Id
    @Column(name = "id_order_state")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrderState;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "identifier", unique = true, nullable = false)
    private String identifier;

    @OneToMany(mappedBy = "orderState")
    private List<UserOrder> userOrders = new ArrayList<>();

    @Builder
    private OrderState(Long idOrderState, String title, String identifier) {
        this.idOrderState = idOrderState;
        this.title = title;
        this.identifier = identifier;
    }
}
