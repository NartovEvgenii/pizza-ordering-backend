package com.pizza.domains;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(price, orderItem.price)
                && Objects.equals(countItems, orderItem.countItems)
                && Objects.equals(pizza, orderItem.pizza)
                && Objects.equals( order == null ? null : order.getIdOrder(),
                                   orderItem.order == null ? null : orderItem.order.getIdOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, countItems, pizza, order == null ? null : order.getIdOrder());
    }
}
