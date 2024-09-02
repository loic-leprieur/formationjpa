package fr.dawan.exercicejpa.entities.bibliotheque;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString


@NamedQueries(value= {
        @NamedQuery(name="Auteur.vivant",
                    query="SELECT a FROM Auteur a WHERE a.deces IS NULL"),
        @NamedQuery(name="Auteur.sanslivre",
                    query="SELECT a FROM Auteur a Join a.livres l WHERE l IS NULL"),
        @NamedQuery(name="Auteur.parNation",
                    query="SELECT a FROM Auteur a WHERE a.nation = :pays")   
})
@Entity
@Table(name="auteurs")
public class Auteur extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @NonNull
    @Column(length = 50, nullable=false)
    private String prenom;
    
    @NonNull
    @Column(length = 50, nullable=false)
    private String nom;
    
    @NonNull
    @Column(nullable=false)
    private LocalDate naissance;

    private LocalDate deces;
    
    @ManyToOne
    @JoinColumn(name = "nation")
    private Nation nation;
    
    @Setter(value = AccessLevel.NONE)
    @Exclude
    @ManyToMany(mappedBy = "auteurs")
    private Set<Livre> livres=new HashSet<>();
    
}
