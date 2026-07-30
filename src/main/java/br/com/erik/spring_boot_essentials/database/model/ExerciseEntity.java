package br.com.erik.spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "exercise")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "muscle_group", nullable = false)
    private String muscleGroup;
}
