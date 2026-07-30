package br.com.erik.spring_boot_essentials.service;

import br.com.erik.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.erik.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.erik.spring_boot_essentials.database.model.StudentEntity;
import br.com.erik.spring_boot_essentials.database.repository.IExerciseRepository;
import br.com.erik.spring_boot_essentials.database.repository.IPhysicalAssessmentRepository;
import br.com.erik.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.erik.spring_boot_essentials.dto.ExerciseDto;
import br.com.erik.spring_boot_essentials.dto.PhysicalAssessmentDto;
import br.com.erik.spring_boot_essentials.dto.PhysicalAssessmentProjection;
import br.com.erik.spring_boot_essentials.exception.BadRequestException;
import br.com.erik.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhysicalAssessmentService {

    private final IStudentRepository studentRepository;
    private final IPhysicalAssessmentRepository physicalAssessmentRepository;

    public void createPhysicalAssessment(PhysicalAssessmentDto physicalAssessmentDto) throws NotFoundException, BadRequestException {
        StudentEntity student= studentRepository.findById(physicalAssessmentDto.getStudentId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));

        PhysicalAssessmentEntity physicalAssessment = student.getPhysicalAssessment();

        if(physicalAssessment != null) {
            throw new BadRequestException("Avaliação Física já cadastrada para este aluno");
        }

        physicalAssessment = PhysicalAssessmentEntity.builder()
                .weight(physicalAssessmentDto.getWeight())
                .height(physicalAssessmentDto.getHeight())
                .bodyFatPercentage(physicalAssessmentDto.getBodyFatPercentage())
                .build();

        student.setPhysicalAssessment(physicalAssessment);
        studentRepository.save(student);
    }

    public List<PhysicalAssessmentProjection> getAllAssessments() {
        return physicalAssessmentRepository.getAllAssessments();
    }

    public Page<PhysicalAssessmentProjection> getAllAssessmentsPageable(Integer page, Integer size) {
        return physicalAssessmentRepository.getAllAssessmentsPageable(PageRequest.of(page, size));
    }
}
