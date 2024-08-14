package fr.dawan.exercicejpa.entities.pizzeria;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="commande_pizza")
public class CommandePizza implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CommandePizzaPK id=new CommandePizzaPK();
    
    @NonNull
    @ManyToOne
    @MapsId("commandeId")
    @Exclude
    private Commande commande;
    
    @NonNull
    @ManyToOne
    @MapsId("pizzaId")
    @Exclude
    private Pizza pizza;
    
    private int quantite;
}
