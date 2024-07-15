package fr.dawan.exercice_jpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@RequiredArgsConstructor

@Table(name = "ingredients")
public class Ingredient extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(nullable = false)
    private String nom;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "ingredient"), inverseJoinColumns = @JoinColumn(name = "pizza"))
    private Set<Pizza> pizzas = new HashSet<>();

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        pizza.getIngredients().add(this);
    }

}
