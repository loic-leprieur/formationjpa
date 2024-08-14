package fr.dawan.jpa.entities.relations;

import java.util.HashSet;
import java.util.Set;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

@Entity
@Table(name = "fournisseurs")
public class Fournisseur extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 80, nullable = false)
    private String nom;

    @Exclude
    @ManyToMany(/* fetch=FetchType.EAGER */cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinTable(name = "fournisseur2article", joinColumns = @JoinColumn(name = "fk_fournisseur"), inverseJoinColumns = @JoinColumn(name = "fk_article"))
    private Set<Article> articles = new HashSet<>();

    public void addArticle(Article article) {
        if (articles.add(article)) {
            article.getFournisseurs().add(this);
        }
    }
}
