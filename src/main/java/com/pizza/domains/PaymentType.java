package com.pizza.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "payment_type")
@Data
public class PaymentType {

    @Id
    @Column(name = "id_payment_type")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPaymentType;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "identifier", unique = true, nullable = false)
    private String identifier;

    @OneToMany(mappedBy = "paymentType")
    private List<UserOrder> userOrders = new ArrayList<>();
}
