package com.pizza.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_order")
@Getter
@Setter
@NoArgsConstructor
public class UserOrder {

    @Id
    @Column(name = "id_order")
    @SequenceGenerator(name = "user_order_id_order_seq", sequenceName = "user_order_id_order_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_order_id_order_seq")
    private Long idOrder;

    @Column(name = "full_price", nullable = false)
    private Double fullPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_order_state")
    private OrderState orderState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_payment_type")
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_address")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private MobUser mobUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    @OneToMany(mappedBy = "userOrder")
    private Set<UserOrderHistory> userOrderHistories = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return Objects.equals(idOrder, userOrder.idOrder) && Objects.equals(fullPrice, userOrder.fullPrice) && Objects.equals(orderState, userOrder.orderState) && Objects.equals(paymentType, userOrder.paymentType) && Objects.equals(address, userOrder.address) && Objects.equals(mobUser, userOrder.mobUser) && Objects.equals(orderItems, userOrder.orderItems);
    }

    @Override
    public int hashCode() {
        return (idOrder != null) ?
                Objects.hash(idOrder)
                : Objects.hash(idOrder, fullPrice, orderState, paymentType, address, mobUser, orderItems);
    }
}
