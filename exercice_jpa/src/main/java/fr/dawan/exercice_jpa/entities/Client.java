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
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Entity
@Table(name = "clients")
public class Client extends Personne implements Serializable {

    public Client(String nom, @NonNull String adresse) {
        super(nom);
        this.adresse = adresse;
    }

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(nullable = false)
    private String adresse;

    @OneToMany(mappedBy = "client")
    private Set<Commande> commandes = new HashSet<>();

    public void addCommande(Commande commande) {
        commandes.add(commande);
        commande.setClient(this);

    }
}
