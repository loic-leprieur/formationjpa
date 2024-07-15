package fr.dawan.exercice_jpa.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table(name = "livres")
public class Livre extends BaseEntity implements Serializable, Comparable<Livre> {

    private static final long serialVersionUID = -835185631968922070L;

    @Column(length = 255, nullable = false)
    private String titre;

    @Column(name = "annee_sortie", nullable = false)
    @Include
    private int anneeSortie;

    @ManyToOne
    private Categorie categorie;

    @ManyToMany
    @JoinTable(name = "auteurs_livres", joinColumns = @JoinColumn(name = "livres_id"), inverseJoinColumns = @JoinColumn(name = "auteurs_id"))
    @Exclude
    private Set<Auteur> auteurs;

    @Override
    public int compareTo(Livre o) {
        
        if(anneeSortie < o.anneeSortie)
            return -1;
        else if(anneeSortie > o.anneeSortie)
            return 1;
        else
            return 0;
    }
}
