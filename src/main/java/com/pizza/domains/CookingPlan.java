package com.pizza.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cooking_plan")
@Getter
@Setter
@NoArgsConstructor
public class CookingPlan {
    @Id
    @Column(name = "id_cooking_plan")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrderState;
    @Column(name = "base_preparation_time", nullable = false)
    private LocalTime basePreparationTime;
    @Column(name = "oven_time", nullable = false)
    private LocalTime ovenTime;
    @Column(name = "packing_time", nullable = false)
    private LocalTime packingTime;
    @OneToMany(mappedBy = "cookingPlan")
    private List<Pizza> pizzaList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookingPlan that = (CookingPlan) o;
        return Objects.equals(idOrderState, that.idOrderState)
                && Objects.equals(basePreparationTime, that.basePreparationTime)
                && Objects.equals(ovenTime, that.ovenTime)
                && Objects.equals(packingTime, that.packingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderState, basePreparationTime, ovenTime, packingTime);
    }
}
