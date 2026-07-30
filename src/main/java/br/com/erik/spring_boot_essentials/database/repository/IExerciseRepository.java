package br.com.erik.spring_boot_essentials.database.repository;

import br.com.erik.spring_boot_essentials.database.model.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {

    List<ExerciseEntity> findAllByMuscleGroup(String muscleGroup);

    // JPQL
    @Query(value = """
        SELECT e 
        FROM ExerciseEntity e 
        WHERE UPPER(e.muscleGroup) = UPPER(:muscleGroup)
    """)
    List<ExerciseEntity> findAllByMuscleGroupJpql(@Param("muscleGroup") String muscleGroup);


    // Native Query
    @NativeQuery(value = """
            SELECT e
            FROM exercise e
            WHERE UPPER(e.muscle_group) = UPPER(:muscleGroup)
        """)
    List<ExerciseEntity> findAllByMuscleGroupNative(@Param("muscleGroup") String muscleGroup);
}