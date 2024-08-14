package fr.dawan.exercicejpa.entities.bibliotheque;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "livres")
public class Livre extends BaseEntity implements Comparable<Livre> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String titre;

    @Include
    @Column(nullable = false, name = "annee_sortie")
    private int anneeSortie;

    @ManyToOne
    private Categorie categorie;

    @Setter(value = AccessLevel.NONE)
    @Exclude
    @ManyToMany
    private Set<Auteur> auteurs = new HashSet<>();

    public Livre(String titre, int anneeSortie) {
        super();
        this.titre = titre;
        this.anneeSortie = anneeSortie;
    }

    @Override
    public int compareTo(Livre o) {
        if(anneeSortie<o.anneeSortie) {
            return -1;
        }else if(anneeSortie>o.anneeSortie) {
            return 1;
        }
        return 0;
    }

}
