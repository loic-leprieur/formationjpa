package fr.dawan.exercice_jpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.dawan.exercice_jpa.enums.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Entity
@Table(name = "pizzas")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Pizza extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Base base = Base.ROUGE;

    @Column(nullable = false)
    @Builder.Default
    private double prix = 10;

    @Column(nullable = true)
    @Lob
    private byte[] photo;

    @ManyToMany(mappedBy = "pizzas")
    @Singular
    private Set<Ingredient> ingredients;

    @OneToMany(mappedBy = "pizza")
    @Builder.Default
    private Set<CommandePizza> commandes = new HashSet<>();

}
