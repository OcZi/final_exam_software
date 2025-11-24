package me.salva.grade.domain.service;

import me.salva.grade.domain.model.Grade;

public class GradeCalculatorDomainService {

    public double calculateFinal(Grade grade) {

        double weighted = grade.getEvaluations()
                .stream()
                .mapToDouble(e -> e.getGrade() * e.getWeight())
                .sum();

        // penalización por asistencia
        if (!grade.hasReachedMinimumClasses()) {
            throw new IllegalStateException("Attendance not met");
        }

        // aplicar extra SOLO si hay >= 2 años con true
        long approvedYears = grade.getAllYearsTeachers()
                .values()
                .stream()
                .filter(Boolean::booleanValue)
                .count();

        if (approvedYears >= 2) {
            weighted += 1.0;
        }

        return Math.min(weighted, 20.0);
    }
}
