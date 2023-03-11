package com.pizza.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_order")
@Data
public class UserOrder {

    @Id
    @Column(name = "id_order")
    @SequenceGenerator(name = "user_order_id_order_seq", sequenceName = "user_order_id_order_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_order_id_order_seq")
    private Long idOrder;

    @Column(name = "full_price", unique = true, nullable = false)
    private Double fullPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_State")
    private OrderState orderState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_address")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private MobUser mobUser;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();;
}
