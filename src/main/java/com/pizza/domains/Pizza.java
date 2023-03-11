package com.pizza.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizza")
@Data
public class Pizza {

    @Id
    @Column(name = "id_pizza")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPizza;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "pizza")
    private List<OrderItem> orderItems = new ArrayList<>();


}
