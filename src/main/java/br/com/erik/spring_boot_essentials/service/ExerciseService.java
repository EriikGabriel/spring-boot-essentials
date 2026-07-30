package br.com.erik.spring_boot_essentials.service;

import br.com.erik.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.erik.spring_boot_essentials.database.repository.IExerciseRepository;
import br.com.erik.spring_boot_essentials.dto.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final IExerciseRepository exerciseRepository;

    public List<ExerciseEntity> findAll() {
        return exerciseRepository.findAll();
    }

    public void save(ExerciseDto exerciseDto) {
        ExerciseEntity exercise = ExerciseEntity.builder()
                .name(exerciseDto.getName())
                .muscleGroup(exerciseDto.getMuscleGroup())
                .build();

        exerciseRepository.save(exercise);
    }

    public List<ExerciseEntity> getExercisesByMuscleGroup(String muscleGroup) {
        return exerciseRepository.findAllByMuscleGroup(muscleGroup);
    }

}
