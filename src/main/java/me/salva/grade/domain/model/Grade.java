package me.salva.software.domain.model;

import lombok.Getter;

@Getter
public class Grade {
    private final double midterm;
    private final double project;
    private final double finalExam;

    public Grade(double midterm, double project, double finalExam) {
        this.midterm = midterm;
        this.project = project;
        this.finalExam = finalExam;
    }
}