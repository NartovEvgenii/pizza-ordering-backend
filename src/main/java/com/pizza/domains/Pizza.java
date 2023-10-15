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
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cooking_plan")
    private CookingPlan cookingPlan;
    @OneToMany(mappedBy = "pizza")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(idPizza, pizza.idPizza)
                && Objects.equals(title, pizza.title)
                && Objects.equals(imagePath, pizza.imagePath)
                && Objects.equals(description, pizza.description)
                && Objects.equals(price, pizza.price)
                && Objects.equals(cookingPlan, pizza.cookingPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPizza, title, imagePath, description, price, cookingPlan);
    }
}
