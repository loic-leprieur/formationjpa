package fr.dawan.jpa.entities.relations;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import jakarta.persistence.CascadeType;
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
import lombok.ToString.Exclude;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "marques")
public class Marque extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 60, nullable = false)
    private String nom;

    @NonNull
    private LocalDate dateCreation;
    
    // Relation 1,n bidirectionnelle -> @OneToMany 
    // C'est la relation retour Marque -> Article
    // pour la réaliser, on utilise un collection qui contient les articles
    // on utilise l'annotation @OneToMany avec l'attribut mappedBy qui a pour valeur
    // le nom de variable d'instance de l'autre coté de la relation -> ici marque
    // dans ce cas, on peut connaitre le marque à partir de l'article et les articles
    // depuis la marque
    @Exclude
    @OneToMany(mappedBy = "marque", cascade= {CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Article> articles=new HashSet<>();
    
    // @OneToMany unidirectionel, il faut ajouter un @JoinColumn , sinon une table de jointure est créé au lieu d'une colonne de jointure
    // @OneToMany
    // @JoinColumn(name="marque_id")
    // private Set<Article> articles=new HashSet<>();
}
