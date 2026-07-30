package br.com.erik.spring_boot_essentials.dto;

import java.math.BigDecimal;

public interface PhysicalAssessmentProjection {
    Integer getStudentId();
    String getStudentName();
    Integer getAssessmentId();
    BigDecimal getWeight();
    BigDecimal getHeight();
    BigDecimal getBodyFatPercentage();
}
