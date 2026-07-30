package br.com.erik.spring_boot_essentials.service;

import br.com.erik.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.erik.spring_boot_essentials.database.model.StudentEntity;
import br.com.erik.spring_boot_essentials.database.model.WorkoutEntity;
import br.com.erik.spring_boot_essentials.database.repository.IExerciseRepository;
import br.com.erik.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.erik.spring_boot_essentials.database.repository.IWorkoutRepository;
import br.com.erik.spring_boot_essentials.dto.ExerciseDto;
import br.com.erik.spring_boot_essentials.dto.WorkoutDto;
import br.com.erik.spring_boot_essentials.exception.BadRequestException;
import br.com.erik.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final IWorkoutRepository workoutRepository;
    private final IExerciseRepository exerciseRepository;
    private final IStudentRepository studentRepository;

    public void createWorkout(WorkoutDto workoutDto) throws NotFoundException, BadRequestException {
        Set<ExerciseEntity> exercises = new HashSet<>();
        StudentEntity student = studentRepository.findById(workoutDto.getStudentId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        WorkoutEntity workout = workoutRepository.findByNameAndStudentId(workoutDto.getName(), workoutDto.getStudentId())
                .orElse(null);

        if(workout != null) {
            throw new BadRequestException("Já existe um treino com este nome para este aluno");
        }

        for (Integer exerciseId : workoutDto.getExercisesIds()) {
            ExerciseEntity exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new NotFoundException(String.format("Exercicio %s não encontrado", exerciseId)));

            exercises.add(exercise);
        }

         workout = WorkoutEntity.builder()
                .name(workoutDto.getName())
                .student(student)
                .exercises(exercises)
                .build();

        workoutRepository.save(workout);
    }
}
