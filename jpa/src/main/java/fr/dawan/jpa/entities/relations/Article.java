package fr.dawan.jpa.entities.relations;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import fr.dawan.jpa.enums.Emballage;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name="articles")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(length = 100,nullable=false)
    private String description;
    
    private double prix;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Emballage emballage;
    
 // @ManyToOne -> Relations 1,n
    // Un article a une marqueet une marque peut avoir plusieurs articles
    
    // Relation @ManyToOne Unidirectionnel
    // on a uniquement une relation Article -> Marque
    // et pas de relation Marque -> Article , on n'a pas accés aux articles depuis le Marque
    @ManyToOne(/*fetch=FetchType.LAZY*/)
    @JoinColumn(name="fk_marque")
    private Marque marque;
    
    @ManyToMany(mappedBy = "articles")
    @Exclude
    private Set<Fournisseur> fournisseurs = new HashSet<>();

    public Article(String description, double prix, Emballage emballage) {
        super();
        this.description = description;
        this.prix = prix;
        this.emballage = emballage;
    }

}
