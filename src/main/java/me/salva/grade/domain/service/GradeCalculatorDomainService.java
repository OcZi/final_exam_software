package me.salva.grade.domain.service;

import me.salva.grade.domain.model.Evaluation;
import me.salva.grade.domain.model.Grade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class GradeCalculatorDomainService {

    private final BigDecimal extraPointsValue = BigDecimal.valueOf(1.0);
    private final BigDecimal maxGrade = BigDecimal.valueOf(20.0);

    public BigDecimal calculateFinalDecimal(Grade grade) {
        if (grade == null) throw new IllegalArgumentException("grade null");

        // weighted sum
        BigDecimal weighted = grade.getExamsStudents().stream()
                .map(e -> BigDecimal.valueOf(e.getGrade()).multiply(BigDecimal.valueOf(e.getWeight())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // attendance rule (RF02)
        if (!grade.isHasReachedMinimumClasses()) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        // extra points rule (RF03): apply if allYearsTeachers non-empty and all true
        Map<Integer, Boolean> approvals = grade.getAllYearsTeachers();
        boolean applyExtra = approvals != null && !approvals.isEmpty() && approvals.values().stream().allMatch(Boolean::booleanValue);
        if (applyExtra) weighted = weighted.add(extraPointsValue);

        // clamp and deterministic rounding to 2 decimals
        if (weighted.compareTo(maxGrade) > 0) weighted = maxGrade;
        return weighted.setScale(2, RoundingMode.HALF_UP);
    }

    // convenience for older callers
    public double calculateFinal(Grade grade) {
        return calculateFinalDecimal(grade).doubleValue();
    }
}
