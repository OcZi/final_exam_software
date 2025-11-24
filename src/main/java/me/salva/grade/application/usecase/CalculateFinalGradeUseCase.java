package me.salva.grade.application.usecase;

import me.salva.grade.application.dto.GradeRequest;
import me.salva.grade.application.dto.GradeResponse;
import me.salva.grade.domain.model.Evaluation;
import me.salva.grade.domain.model.Grade;
import me.salva.grade.domain.repository.GradeRepository;
import me.salva.grade.domain.service.GradeCalculatorDomainService;

import java.math.BigDecimal;
import java.util.List;

public class CalculateFinalGradeUseCase {

    private final GradeRepository repository;
    private final GradeCalculatorDomainService domainService;

    public CalculateFinalGradeUseCase(GradeRepository repository, GradeCalculatorDomainService domainService) {
        this.repository = repository;
        this.domainService = domainService;
    }

    public GradeResponse execute(GradeRequest request) {
        Grade grade = repository.findById(request.studentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + request.studentId()));

        List<Evaluation> evaluations = grade.getExamsStudents();
        BigDecimal weightedAverage = evaluations.stream()
                .map(e -> BigDecimal.valueOf(e.getGrade()).multiply(BigDecimal.valueOf(e.getWeight())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal finalGrade = domainService.calculateFinalDecimal(grade);

        boolean appliedAttendancePenalty = !grade.isHasReachedMinimumClasses();
        boolean appliedExtraPoints = finalGrade.compareTo(weightedAverage) > 0 && !appliedAttendancePenalty;

        return new GradeResponse(
                grade.getStudentId(),
                weightedAverage.doubleValue(),
                finalGrade.doubleValue(),
                appliedAttendancePenalty,
                appliedExtraPoints,
                evaluations
        );
    }
}
