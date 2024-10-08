package fr.dawan.requetejpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.envers.Audited;

import fr.dawan.requetejpa.enums.Emballage;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
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
@ToString(callSuper = true)

@NamedQueries({ @NamedQuery(name = "Article.prixLess", query = "SELECT a FROM Article a WHERE a.prix < ?1"),
        @NamedQuery(name = "Article.descriptionPrefix", query = "SELECT a FROM Article a WHERE a.description LIKE CONCAT(:modele, '%')") })

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "Article.nbArticle", procedureName = "count_article_by_marque", parameters = {
                @StoredProcedureParameter(name = "id_marque", mode = ParameterMode.IN, type = Long.class),
                @StoredProcedureParameter(name = "nb_article", mode = ParameterMode.OUT, type = Integer.class), }) })


@Entity
@Table(name = "articles")
@Audited(withModifiedFlag = true)/*, targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)*/
@Cacheable
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
