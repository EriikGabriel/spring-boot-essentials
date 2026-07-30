package br.com.erik.spring_boot_essentials.controller;

import br.com.erik.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.erik.spring_boot_essentials.dto.ExerciseDto;
import br.com.erik.spring_boot_essentials.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exercises")
@RequiredArgsConstructor
@Validated
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseEntity> findAll() {
        return exerciseService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercise(@Valid @RequestBody ExerciseDto exerciseDto) {
        exerciseService.save(exerciseDto);
    }

    @GetMapping("/groups/{muscleGroup}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseEntity> getExercisesByMuscleGroup(@PathVariable String muscleGroup) {
       return exerciseService.getExercisesByMuscleGroup(muscleGroup);
    }
}
