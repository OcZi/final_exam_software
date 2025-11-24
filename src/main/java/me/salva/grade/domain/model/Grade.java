package me.salva.grade.domain.model;

import java.math.BigDecimal;
import java.util.*;

public class Grade {
    private final String studentId;

    // nombres literales requeridos por el enunciado
    private final List<Evaluation> examsStudents = new ArrayList<>();
    private boolean hasReachedMinimumClasses;
    private final Map<Integer, Boolean> allYearsTeachers = new HashMap<>();

    public Grade(String studentId) {
        this.studentId = Objects.requireNonNull(studentId);
    }

    // RF01 + RNF01
    public void addEvaluation(Evaluation e) {
        Objects.requireNonNull(e);
        if (examsStudents.size() >= 10) {
            throw new IllegalStateException("Max 10 evaluations allowed");
        }
        // validate individual weight
        if (e.getWeight() <= 0 || e.getWeight() > 1) throw new IllegalArgumentException("invalid weight");
        examsStudents.add(e);
    }

    public List<Evaluation> getExamsStudents() { return Collections.unmodifiableList(examsStudents); }

    public String getStudentId() { return studentId; }

    // RF02
    public boolean isHasReachedMinimumClasses() { return hasReachedMinimumClasses; }
    public void setHasReachedMinimumClasses(boolean hasReachedMinimumClasses) { this.hasReachedMinimumClasses = hasReachedMinimumClasses; }

    // RF03
    public Map<Integer, Boolean> getAllYearsTeachers() { return allYearsTeachers; }
    public void setTeacherApprovalForYear(int year, boolean approved) { allYearsTeachers.put(year, approved); }

    public double totalWeight() {
        return examsStudents.stream().mapToDouble(Evaluation::getWeight).sum();
    }
}
