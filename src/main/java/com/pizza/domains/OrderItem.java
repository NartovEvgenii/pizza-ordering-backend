package com.pizza.domains;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {
    @Id
    @Column(name = "id_order_item")
    @SequenceGenerator(name = "order_item_id_order_item_seq", sequenceName = "order_item_id_order_item_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_order_item_seq")
    private Long idOrderItem;

    @Column(name = "price")
    private Double price;

    @Column(name = "count_items")
    private Integer countItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pizza")
    private Pizza pizza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order")
    private UserOrder order;
}
