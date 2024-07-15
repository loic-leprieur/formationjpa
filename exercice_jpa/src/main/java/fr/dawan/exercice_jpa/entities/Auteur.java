package fr.dawan.exercice_jpa.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@NamedQueries({ @NamedQuery(name = "Auteur.findAlive", query = "SELECT a FROM Auteur a WHERE a.dateDeces IS NULL"),
    @NamedQuery(name = "Auteur.findByNoBook", query = "SELECT a FROM Auteur a WHERE a.livres IS EMPTY"),
        @NamedQuery(name = "Auteur.findByNation", query = "SELECT a FROM Auteur a WHERE a.nationnalite = :nation") })

@Entity
@Table(name = "auteurs")
public class Auteur extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1025975851576420714L;

    @Column(length = 50, nullable = false)
    private String nom;

    @Column(length = 50, nullable = false)
    private String prenom;

    @Column(name = "naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "deces")
    private LocalDate dateDeces;

    @ManyToOne
    @JoinColumn(name = "nation")
    private Nation nationnalite;

    @ManyToMany(mappedBy = "auteurs")
    @Exclude
    private Set<Livre> livres;

}
