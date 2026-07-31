package br.com.erik.spring_boot_essentials.controller;

import br.com.erik.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.erik.spring_boot_essentials.dto.StudentDto;
import br.com.erik.spring_boot_essentials.exception.BadRequestException;
import br.com.erik.spring_boot_essentials.exception.NotFoundException;
import br.com.erik.spring_boot_essentials.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercise(@Valid @RequestBody StudentDto studentDto) throws BadRequestException {
        studentService.createStudent(studentDto);
    }

    @PreAuthorize("#studentId == authentication.principal.id or hasRole('ADMIN')")
    @GetMapping("/{studentId}/assessment")
    @ResponseStatus(HttpStatus.OK)
    public PhysicalAssessmentEntity getPhysicalAssessment(@PathVariable Integer studentId) throws NotFoundException {
        return studentService.getStudentAssessment(studentId);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Integer studentId) throws NotFoundException {
        studentService.deleteStudent(studentId);
    }


}
