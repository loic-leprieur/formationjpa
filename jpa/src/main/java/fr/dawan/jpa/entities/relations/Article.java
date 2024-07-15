package fr.dawan.jpa.entities.relations;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import fr.dawan.jpa.enums.Emballage;
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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "articles")
public class Article extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 100, nullable = false)
    private String description;

    private double prix;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NonNull
    private Emballage emballage;

    @ManyToOne
    @JoinColumn(name = "fk_marque")
    @Exclude
    private Marque marque;

    public Article(String description, double prix, Emballage emballage) {
        super();
        this.description = description;
        this.prix = prix;
        this.emballage = emballage;
    }

    @ManyToMany(mappedBy = "articles")
    @Exclude
    private Set<Fournisseur> fournisseurs = new HashSet<>();;

}
