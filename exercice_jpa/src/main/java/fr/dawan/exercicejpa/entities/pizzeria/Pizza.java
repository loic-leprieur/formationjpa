package fr.dawan.exercicejpa.entities.pizzeria;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.dawan.exercicejpa.enums.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Builder

@Entity
@Table(name = "pizzas")
public class Pizza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;
    

    @Column(length = 60, nullable = false)
    private String nom;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Base base;


    private double prix;

    @Lob
    @Column(length = 60000)
    private byte[] photo;
    

    @Singular
    @ManyToMany
    private Set<Ingredient> ingredients;
    
    @Builder.Default
    @OneToMany(mappedBy="pizza")
    private Set<CommandePizza> commandes=new HashSet<>();

}
