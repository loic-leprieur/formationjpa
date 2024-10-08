package fr.dawan.jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@Table(name="entreprises")
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="ent_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="ent_seq" )
    private long id;
    
    private String nom;
}
