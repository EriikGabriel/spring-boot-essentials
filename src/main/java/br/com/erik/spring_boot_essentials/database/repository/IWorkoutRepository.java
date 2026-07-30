package br.com.erik.spring_boot_essentials.database.repository;

import br.com.erik.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.erik.spring_boot_essentials.database.model.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWorkoutRepository extends JpaRepository<WorkoutEntity, Integer> {
    Optional<WorkoutEntity> findByNameAndStudentId(String name, Integer studentId);

}
