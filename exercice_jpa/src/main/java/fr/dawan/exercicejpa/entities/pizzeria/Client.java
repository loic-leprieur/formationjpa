package fr.dawan.exercicejpa.entities.pizzeria;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "clients")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;
    
    @NonNull
    @Column(length = 50, nullable = false)
    private String nom;
    
    @NonNull
    @Column(length = 100, nullable = false)
    private String adresse;
    
    @OneToMany(mappedBy = "client")
    private Set<Commande> commandes=new HashSet<>();
    
    public void addCommande(Commande commande) {
        if(commandes.add(commande)) {
            commande.setClient(this);
        }
    }
}
