package fr.dawan.jpa.entities.relations;

import java.io.Serializable;
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
import lombok.EqualsAndHashCode.Exclude;
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

@Entity
@Table(name = "marques")
public class Marque extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 60, nullable = false)
    private String nom;

    @NonNull
    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "marque", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    @Exclude
    private Set<Article> articles = new HashSet<>();

}
