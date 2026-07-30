package br.com.erik.spring_boot_essentials.controller;

import br.com.erik.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.erik.spring_boot_essentials.dto.StudentDto;
import br.com.erik.spring_boot_essentials.dto.WorkoutDto;
import br.com.erik.spring_boot_essentials.exception.BadRequestException;
import br.com.erik.spring_boot_essentials.exception.NotFoundException;
import br.com.erik.spring_boot_essentials.service.StudentService;
import br.com.erik.spring_boot_essentials.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/workouts")
@RequiredArgsConstructor
@Validated
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWorkout(@Valid @RequestBody WorkoutDto workoutDto) throws BadRequestException, NotFoundException {
        workoutService.createWorkout(workoutDto);
    }
}
