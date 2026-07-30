package br.com.erik.spring_boot_essentials.database.repository;

import br.com.erik.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.erik.spring_boot_essentials.dto.PhysicalAssessmentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;

public interface IPhysicalAssessmentRepository extends JpaRepository<PhysicalAssessmentEntity, Integer> {

    @NativeQuery(value = """
        SELECT s.id                             studentId,
               s.name                           studentName,
               pa.id                            assessmentId,
               pa.weight                        as weight,
               pa.height                        as height,
               pa.body_fat_percentage           bodyFatPercentage
        FROM physical_assessment pa
        INNER JOIN student s
        ON s.assessment_id = pa.id
        """)
    List<PhysicalAssessmentProjection> getAllAssessments();

    @NativeQuery(value = """
        SELECT s.id                             studentId,
               s.name                           studentName,
               pa.id                            assessmentId,
               pa.weight                        as weight,
               pa.height                        as height,
               pa.body_fat_percentage           bodyFatPercentage
        FROM physical_assessment pa
        INNER JOIN student s
        ON s.assessment_id = pa.id
        """,
            countQuery = """
        SELECT count(pa.id)
        FROM physical_assessment pa
        INNER JOIN student s
        ON s.assessment_id = pa.id
        """)
    Page<PhysicalAssessmentProjection> getAllAssessmentsPageable(Pageable pageable);
}
