package fr.dawan.jpa.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import fr.dawan.jpa.enums.TypeContrat;
import fr.dawan.jpa.enums.TypeTelephone;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "employees")
public class Employe extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_gen")
//    @TableGenerator(name = "table_gen")

    @SequenceGenerator(name = "emp_seq", initialValue = 1000, allocationSize = 30000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")

    @Id
    private long id;

    @Version
    private int version;

    @Column(length = 60)
    private String prenom;

    @Column(length = 60, nullable = false)
    private String nom;

//    @Column(precision = 6, scale = 2)
//    private BigDecimal salaire;

    @Column(columnDefinition = "decimal(6,2) default '1200.0'")
    @Builder.Default
    private double salaire = 1400;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    @Builder.Default
    private TypeContrat contrat = TypeContrat.CDI;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Lob
    @Column(name = "photo_identite", length = 65000)
    private byte[] photoIdentite;

    @Column(unique = true, nullable = false)
    private String email;

    @Embedded
    private Adresse adressePerso;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "rue", column = @Column(name = "rue_pro")),
            @AttributeOverride(name = "ville", column = @Column(name = "ville_pro", length = 150)),
            @AttributeOverride(name = "codePostal", column = @Column(name = "code_postal_pro", length = 15)) })
    private Adresse adressePro;

    @ElementCollection(targetClass = String.class)
    @Singular
    @CollectionTable(name = "employes_medicaments", joinColumns = @JoinColumn(name = "emp_id"))
    @Column(name = "traitement")
    private List<String> medicaments;

    @ElementCollection
    @CollectionTable(name = "telephones", joinColumns = @JoinColumn(name = "emp_id"))
    @MapKeyColumn(name = "type")
    @Column(name = "numero")
    @MapKeyEnumerated(EnumType.STRING)
    @Singular
    private Map<TypeTelephone, String> telephones;

    // @Transient
    private transient int nePasPersister;

    public Employe(String prenom, String nom, double salaire, TypeContrat contrat, LocalDate dateNaissance,
            byte[] photoIdentite, String email, Adresse adressePerso, Adresse adressePro) {
        super();
        this.prenom = prenom;
        this.nom = nom;
        this.salaire = salaire;
        this.contrat = contrat;
        this.dateNaissance = dateNaissance;
        this.photoIdentite = photoIdentite;
        this.email = email;
        this.adressePerso = adressePerso;
        this.adressePro = adressePro;
    }

}
