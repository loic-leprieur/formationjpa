package fr.dawan.jpa.entities.heritage;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "personnes")
public class Personne extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String prenom;

    private String nom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

}
