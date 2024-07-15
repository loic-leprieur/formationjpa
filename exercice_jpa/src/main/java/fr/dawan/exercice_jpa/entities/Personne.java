package fr.dawan.exercice_jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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

@MappedSuperclass
public abstract class Personne extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 60, nullable = false)
    private String nom;

}
