package fr.dawan.exercice_jpa.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "commandes")

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class Commande extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "heure_commande", nullable = false)
    @NonNull
    private LocalDateTime heureCommande;

    @NonNull
    @Column(name = "heure_livraison", nullable = false)
    private LocalDateTime heureLivraison;

    @ManyToOne
    private Livreur livreur;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande")
    private Set<CommandePizza> pizzas = new HashSet<>();

    public CommandePizza addPizza(Pizza pizza, int quantite) {

        CommandePizza cmdPizza = new CommandePizza(this, pizza);
        pizzas.add(cmdPizza);
        pizza.getCommandes().add(cmdPizza);
        cmdPizza.setQuantite(quantite);

        return cmdPizza;
    }

}
