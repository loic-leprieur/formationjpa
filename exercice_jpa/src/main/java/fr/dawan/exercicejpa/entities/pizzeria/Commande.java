package fr.dawan.exercicejpa.entities.pizzeria;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "commandes")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    @NonNull
    @Column(name = "heure_commande", nullable = false)
    private LocalDateTime heureCommande;

    @NonNull
    @Column(name = "heure_livraison", nullable = false)
    private LocalDateTime heureLivraison;
    
    @ManyToOne
    private Livreur livreur;
    
    @ManyToOne
    private Client client;
    
    @OneToMany(mappedBy = "commande")
    private Set<CommandePizza> pizzas=new HashSet<>();
    
    public CommandePizza addPizza(Pizza pizza, int quantite) {
        CommandePizza cmd=new CommandePizza(this,pizza);
        pizzas.add(cmd);
        pizza.getCommandes().add(cmd);
        cmd.setQuantite(quantite);
        return cmd;
    }
}
