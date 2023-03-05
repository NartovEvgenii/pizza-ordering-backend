package com.pizza.domains;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mobile_user")
@Data
public class MobUser {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_general_address")
    private Address generalAddress;

    @OneToMany(mappedBy = "mobUser")
    private List<UserOrder> userOrders = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_has_address",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_address"))
    private List<Address> addresses = new ArrayList<>();
}
