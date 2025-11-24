package me.salva.software.application.usecase;


import me.salva.software.domain.model.Grade;
import me.salva.software.application.dto.GradeRequest;
import me.salva.software.application.dto.GradeResponse;
import me.salva.software.domain.service.GradeCalculatorDomainService;

public class CalculateFinalGradeUseCase {


    private final GradeCalculatorDomainService domainService;


    public CalculateFinalGradeUseCase(GradeCalculatorDomainService domainService) {
        this.domainService = domainService;
    }


    public GradeResponse execute(GradeRequest request) {
        Grade g = new Grade(request.midterm(), request.project(), request.finalExam());
        double finalGrade = domainService.calculateFinal(g);
        return new GradeResponse(finalGrade);
    }
}