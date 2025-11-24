package me.salva.grade.application.dto;

public record RegisterEvaluationRequest(
        String studentId,
        double grade,
        double weight
) {}
