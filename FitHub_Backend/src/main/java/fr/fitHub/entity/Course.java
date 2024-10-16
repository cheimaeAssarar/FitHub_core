package fr.fitHub.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@EqualsAndHashCode
@Data //Génère les getters, setters, toString, equals, et hashCode pour tous les champs.
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String coach;
    private String schedule;

}
