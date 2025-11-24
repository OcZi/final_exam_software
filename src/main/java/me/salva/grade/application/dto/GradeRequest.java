package me.salva.grade.application.dto;

/**
 * Request DTO for the use case. Here we ask to calculate by studentId.
 */
public record GradeRequest(String studentId) {}
