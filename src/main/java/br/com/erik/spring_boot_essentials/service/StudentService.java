package br.com.erik.spring_boot_essentials.service;

import br.com.erik.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.erik.spring_boot_essentials.database.model.StudentEntity;
import br.com.erik.spring_boot_essentials.database.model.WorkoutEntity;
import br.com.erik.spring_boot_essentials.database.repository.IPhysicalAssessmentRepository;
import br.com.erik.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.erik.spring_boot_essentials.database.repository.IWorkoutRepository;
import br.com.erik.spring_boot_essentials.dto.PhysicalAssessmentDto;
import br.com.erik.spring_boot_essentials.dto.StudentDto;
import br.com.erik.spring_boot_essentials.exception.BadRequestException;
import br.com.erik.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final IPhysicalAssessmentRepository physicalAssessmentRepository;
    private final IWorkoutRepository workoutRepository;
    private final IStudentRepository studentRepository;

    public void createStudent(StudentDto studentDto) throws BadRequestException {
        StudentEntity student = studentRepository.findByEmail(studentDto.getEmail())
                .orElse(null);

        if (student != null) {
            throw  new BadRequestException("Aluno já cadastrado com esse email");
        }

        student = StudentEntity.builder()
                .name(studentDto.getName())
                .email(studentDto.getEmail())
                .build();

        studentRepository.save(student);
    }

    public PhysicalAssessmentEntity getStudentAssessment(Integer studentId) throws NotFoundException {
        StudentEntity student = studentRepository.findByIdFetch(studentId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        PhysicalAssessmentEntity assessment = student.getPhysicalAssessment();

        if(assessment == null) {
            throw new NotFoundException("Avaliação Física não encontrada para este aluno");
        }

        return assessment;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteStudent(Integer studentId) throws NotFoundException {
        StudentEntity student = studentRepository.findByIdFetch(studentId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        List<Integer> studentWorkoutsIds = student.getWorkouts().stream()
                .map(WorkoutEntity::getId)
                .toList();

        workoutRepository.deleteAllById(studentWorkoutsIds);

        studentRepository.deleteById(studentId);

        physicalAssessmentRepository.deleteById(student.getPhysicalAssessment().getId());
    }
}
