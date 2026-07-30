package br.com.erik.spring_boot_essentials.database.repository;

import br.com.erik.spring_boot_essentials.database.model.StudentEntity;
import br.com.erik.spring_boot_essentials.database.model.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IStudentRepository extends JpaRepository<StudentEntity, Integer> {
    Optional<StudentEntity> findByEmail(String email);

    @Query(value = """
        SELECT s 
        FROM StudentEntity s 
        JOIN FETCH s.physicalAssessment
        WHERE s.id = :studentId
    """)
    Optional<StudentEntity> findByIdFetch(Integer studentId);
}
