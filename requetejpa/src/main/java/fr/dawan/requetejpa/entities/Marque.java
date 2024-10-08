package fr.dawan.requetejpa.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.envers.Audited;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
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
@RequiredArgsConstructor
@Getter
@Setter
@ToString

//@NamedEntityGraphs({
//    @NamedEntityGraph(name = "marqueGraph", attributeNodes = {
//            
//            @NamedAttributeNode(value = "articles",
//                                subgraph= "sub_article_fournisseur")
//    
//    }, subgraphs= {
//            @NamedSubgraph(name = "sub_article_fournisseur",
//                                 attributeNodes = { @NamedAttributeNode("fournisseurs")}), 
//                  @NamedSubgraph(name = "sub_fournisseur_logo",
//                                 attributeNodes = { @NamedAttributeNode("logo")}) 
//            })
//})


@NamedNativeQueries({
    @NamedNativeQuery(name = "Marque.findByNom",
            query = "SELECT * FROM marques m WHERE m.nom = :nom", resultClass = Marque.class)
})

@Entity
@Table(name = "marques")
@Audited(withModifiedFlag = true)
public class Marque extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 60, nullable = false)
    private String nom;

    @NonNull
    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "marque", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    @lombok.ToString.Exclude
    private Set<Article> articles = new HashSet<>();

}
