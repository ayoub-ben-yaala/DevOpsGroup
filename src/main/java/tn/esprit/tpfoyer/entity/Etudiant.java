package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor


@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEtudiant;

    String nomEtudiant;
    String prenomEtudiant;
    long cinEtudiant;
    String dateNaissance;

    @ManyToMany(mappedBy = "etudiants")
    Set<Reservation> reservations;

    public Etudiant(long l, String jane, String smith, int i, String aa) {
    }

    public Etudiant(long l, String john, String doe, String number) {
    }

    public Etudiant(long l, String john, String doe, String number, String aa) {
    }
}



