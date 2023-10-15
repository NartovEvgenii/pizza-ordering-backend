package com.pizza.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_order_history")
@Getter
@Setter
@NoArgsConstructor
public class UserOrderHistory {

    @Id
    @Column(name = "id_order_history")
    @SequenceGenerator(name = "or_hist_id_or_hist_seq", sequenceName = "or_hist_id_or_hist_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "or_hist_id_or_hist_seq")
    private Long idOrderHistory;

    @Column(name = "change_time")
    private LocalDateTime changeTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_order_state")
    private OrderState orderState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_order")
    private UserOrder userOrder;
}
