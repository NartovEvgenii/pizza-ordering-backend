package com.pizza.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @Column(name = "id_address")
    @SequenceGenerator(name = "address_id_address_seq", sequenceName = "address_id_address_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_address_seq")
    private Long idAddress;

    @Column(name = "title", unique = true, nullable = false)
    private String title;
    @OneToMany(mappedBy = "generalAddress")
    private List<MobUser> usersGeneralAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "address")
    private List<UserOrder> userOrders = new ArrayList<>();

    @ManyToMany(mappedBy = "addresses")
    private List<MobUser> mobUsers = new ArrayList<>();

    public Address(String title) {
        this.title = title;
    }
}
