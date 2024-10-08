package fr.dawan.requetejpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
@Setter
@Getter
@ToString

@Entity
@Table(name = "fournisseurs")
@Audited(withModifiedFlag = true)
public class Fournisseur extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 80, nullable = false)
    private String nom;

    @ManyToMany
    @Exclude
    @JoinTable(name = "fournisseur2article", joinColumns = @JoinColumn(name = "fk_fournisseur"), inverseJoinColumns = @JoinColumn(name = "fk_article"))
    private Set<Article> articles = new HashSet<>();
    
    @OneToOne
    private Logo logo;

    public void addArticle(Article article) {
        articles.add(article);
        article.getFournisseurs().add(this);
    }

}
