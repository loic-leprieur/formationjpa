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
import lombok.ToString;
import lombok.ToString.Exclude;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "categories")
public class Categorie extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5002095772686497403L;

    @Column(length = 255, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "categorie")
    @Exclude
    private Set<Livre> livres;

}
