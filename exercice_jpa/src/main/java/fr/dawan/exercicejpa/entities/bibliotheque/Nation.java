package fr.dawan.exercicejpa.entities.bibliotheque;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

@Entity
@Table(name = "nations")
public class Nation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Column(length = 100, nullable = false)
    private String nom;

    @Setter(value = AccessLevel.NONE)
    @Exclude
    @OneToMany(mappedBy = "nation")
    private Set<Auteur> auteurs = new HashSet<>();
}
