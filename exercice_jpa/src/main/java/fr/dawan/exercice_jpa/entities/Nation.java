package fr.dawan.exercice_jpa.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "nations")
public class Nation extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 100, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "nationnalite")
    private Set<Auteur> auteurs;
}
