package br.com.erik.spring_boot_essentials.controller;

import br.com.erik.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.erik.spring_boot_essentials.dto.ExerciseDto;
import br.com.erik.spring_boot_essentials.dto.PhysicalAssessmentDto;
import br.com.erik.spring_boot_essentials.dto.PhysicalAssessmentProjection;
import br.com.erik.spring_boot_essentials.exception.BadRequestException;
import br.com.erik.spring_boot_essentials.exception.NotFoundException;
import br.com.erik.spring_boot_essentials.service.ExerciseService;
import br.com.erik.spring_boot_essentials.service.PhysicalAssessmentService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/assessments")
@RequiredArgsConstructor
@Validated
public class PhysicalAssessmentController {

    private final PhysicalAssessmentService physicalAssessmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPhysicalAssessment(@Valid @RequestBody PhysicalAssessmentDto physicalAssessmentDto) throws NotFoundException, BadRequestException {
        physicalAssessmentService.createPhysicalAssessment(physicalAssessmentDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PhysicalAssessmentProjection> getAllAssessments() {
        return physicalAssessmentService.getAllAssessments();
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<PhysicalAssessmentProjection> getAllAssessmentsPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return physicalAssessmentService.getAllAssessmentsPageable(page, size);
    }
}
