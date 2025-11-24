package me.salva.software.domain.service;


import me.salva.software.domain.model.Grade;

public class GradeCalculatorDomainService {
    public double calculateFinal(Grade grade) {
        return (grade.getMidterm() * 0.3) +
                (grade.getProject() * 0.3) +
                (grade.getFinalExam() * 0.4);
    }
}