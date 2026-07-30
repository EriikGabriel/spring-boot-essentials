package br.com.erik.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PhysicalAssessmentDto {
    @NotNull
    private Integer studentId;

    @NotNull
    private BigDecimal weight;

    @NotNull
    private BigDecimal height;

    @NotNull
    private BigDecimal bodyFatPercentage;
}
