package fr.dawan.jpa.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

// Une entité doit :
// - être annoté avec @Entity
// - avoir un attribut qui représente la clé primaire annoté avec @Id
// - implémenté l'interface Serializable
// - avoir obligatoirement un contructeur par défaut

@Entity
@Table(name = "employees") // L'annotation @Table permet de modifier le nom de la table, sinon elle prend
                           // le nom de la classe

@Builder
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;

    // Génération automatique de la clé primaire
    // 1 -> AUTO (par défaut): Hibernate choisit une stratégie en fonction du SGBD
    // @GeneratedValue

    // 2 -> IDENTITY: c'est la base de donnée qui va générer la clé primaire
    // Mysql, MariaDB -> AUTO_INCREMENT
    // @GeneratedValue(strategy = GenerationType.IDENTITY)

    // 3 -> TABLE: Hibernate crée une table (ici table_gen) qui stocke les noms et
    // les valeurs des séquences à utiliser avec l’annotation @TableGenerator
    // @TableGenerator(name="table_gen")
    // @GeneratedValue(strategy = GenerationType.TABLE,generator ="table_gen")

    // 4 -> SEQUENCE: La génération se fait par une séquence définie par le système de gestion de bdd
    // utiliser avec l’annotation @SequenceGenerator
    @SequenceGenerator(name = "emp_seq", initialValue = 1000, allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    
    // @Id -> Clé primaire simple
    @Id
    private long id;

 // Gestion de la concurrence Optimistic (Versioned) => @Version
    @Version
    private int version;

    // L'attribut @Column permet pour définir plus précisément la colonne
    @Column(length = 60) // l'attribut length permet modifier de la longueur d'une chaine de caractère ou  d'un @lob
    private String prenom; // Sinon par défaut 255 caractères ou octets

    @Column(length = 60, nullable = false) // l'attribut nullable permet de définir si le champ peut être null
                                           // (optionnel) par défaut true
    private String nom;

    // @Column(columnDefinition = " DOUBLE default 1200.0")
    private double salaire = 1400.0;

//    @Column(precision =6,scale=2)
//    private BigDecimal salaire;

    // Une énumération peut être stocké sous forme numérique EnumType.ORDINAL (par défaut)
    // ou sous forme de chaine de caractères EnumType.STRING
    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private TypeContrat contrat = TypeContrat.CDI;

    // LocalDate, LocalTime, LocalDateTime sont supportés par hibernate depuis la version 5
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    // @Lob => pour stocker des données binaires dans la bdd (image, ...) BLOB ou un long texte CLOB
    @Lob
    @Column(name = "photo_identite", length = 65000)
    // l'attribut length permet de définir la taille du blob => ici un MediumBlob
    // max 16 mo
    // pour MYSQL : par défaut un Tinyblob -> 255, Blob-> 65535, MediumBlob -> 16 mo, LongBlob -> 4,29 Go
    private byte[] photoIdentite; // BLOB -> tableau de byte , CLOB -> tableau de caractère

    @Column(unique = true, nullable = false)
    private String email;

    // @ Embedded => pour utiliser une classe intégrable
    @Embedded
    private Adresse adressePerso;

    // Pour utiliser plusieurs fois la même classe imbriqué dans la même entitée
    // On aura plusieurs fois le même nom de colonne dans la table => erreur
    // On pourra renommé les colonnes avec des annotations @AttributeOverride placé
    // dans une annotation @AttributeOverrides
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "rue", column = @Column(name = "rue_pro")),
            @AttributeOverride(name = "ville", column = @Column(name = "ville_pro", length = 150)),
            @AttributeOverride(name = "codePostal", column = @Column(name = "code_postal_pro", length = 15)) })
    private Adresse adressePro;

    // Les attributs @Transient ne sont transient ne sont pas persister
    // sinon tous les autres sont par défaut persistant 
    // @Transient
    private transient int nePasPersister;

    // Mapping d'une collection simple (String, LocalDate, Integer,Double ...)
    @Singular
    @ElementCollection(targetClass = String.class) // @ElementCollection -> par défaut, génére une table NomClasse_nomVariable
    // @CollectionTable -> permet de personaliser de la table name: nom de la table, joinColuns -> nom de la colonne de jointure 
    @CollectionTable(name = "employees_medicaments", joinColumns = @JoinColumn(name = "emp_id"))
    @Column(name = "traitement")
    private List<String> medicaments;

    @Singular
    @ElementCollection 
    @CollectionTable(name = "telephones", joinColumns = @JoinColumn(name = "emp_id"))
    @MapKeyColumn(name = "type")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "numero")
    private Map<TypeTelephone, String> telephones;
}
