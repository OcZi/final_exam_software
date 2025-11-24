package me.salva.grade.domain.model;

import java.util.Objects;

public class Evaluation {
    private final double grade;  // 0..20
    private final double weight; // 0..1

    public Evaluation(double grade, double weight) {
        if (grade < 0 || grade > 20) throw new IllegalArgumentException("grade must be 0..20");
        if (weight <= 0 || weight > 1) throw new IllegalArgumentException("weight must be >0 and <=1");
        this.grade = grade;
        this.weight = weight;
    }

    public double getGrade() {
        return grade;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "grade=" + grade + ", weight=" + weight + '}';
    }
}
