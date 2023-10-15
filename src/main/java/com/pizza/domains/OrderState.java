package com.pizza.domains;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_state")
@Getter
@Setter
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

    @OneToMany(mappedBy = "orderState")
    private List<UserOrderHistory> userOrderHistories = new ArrayList<>();

    @Builder
    private OrderState(Long idOrderState, String title, String identifier) {
        this.idOrderState = idOrderState;
        this.title = title;
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderState that = (OrderState) o;
        return Objects.equals(title, that.title)
                && Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, identifier);
    }
}
