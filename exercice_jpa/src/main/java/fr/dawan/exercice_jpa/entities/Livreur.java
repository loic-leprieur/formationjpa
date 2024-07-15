package fr.dawan.exercice_jpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Table(name = "livreurs")
@Entity
public class Livreur extends Personne implements Serializable {

    public Livreur(String nom, String telephone) {
        super(nom);
        this.telephone = telephone;
    }

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 10, nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "livreur")
    private Set<Commande> commandes = new HashSet<>();

    public void addCommande(Commande commande) {
        commandes.add(commande);
        commande.setLivreur(this);
    }
}
