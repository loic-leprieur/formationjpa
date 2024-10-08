package fr.dawan.jpa.entities;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
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
@Table(name = "personne_adresse")
public class PersonneAdresse implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PersonneAdressePK perAdrPk;

    private String prenom;

    private String nom;

    private String adresse;

}
