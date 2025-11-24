package me.salva.grade.application.dto;

import me.salva.grade.domain.model.Evaluation;
import java.util.List;

/**
 * Response DTO with details (RF05).
 */
public record GradeResponse(
        String studentId,
        double weightedAverage,
        double finalGrade,
        boolean appliedAttendancePenalty,
        boolean appliedExtraPoints,
        List<Evaluation> evaluations
) {}
