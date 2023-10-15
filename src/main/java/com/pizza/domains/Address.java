package com.pizza.domains;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address")
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(idAddress, address.idAddress) && Objects.equals(title, address.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAddress, title);
    }
}
