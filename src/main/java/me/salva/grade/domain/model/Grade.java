package me.salva.grade.domain.model;

import java.util.*;

/**
 * Aggregate root: Grade (represents a student' grading context)
 */
public class Grade {
    private final String studentId;
    private final List<Evaluation> evaluations = new ArrayList<>();
    private boolean hasReachedMinimumClasses;
    private final Map<Integer, Boolean> allYearsTeachers = new HashMap<>();

    public Grade(String studentId) {
        this.studentId = Objects.requireNonNull(studentId);
    }

    // Behavior: add evaluation (RF01)
    public void addEvaluation(Evaluation e) {
        if (evaluations.size() >= 10) {
            throw new IllegalStateException("Max evaluations (10) reached");
        }
        Objects.requireNonNull(e);
        evaluations.add(e);
    }

    public List<Evaluation> getEvaluations() {
        return Collections.unmodifiableList(evaluations);
    }

    public String getStudentId() {
        return studentId;
    }

    public boolean hasReachedMinimumClasses() {
        return hasReachedMinimumClasses;
    }

    // RF02
    public void setHasReachedMinimumClasses(boolean reached) {
        this.hasReachedMinimumClasses = reached;
    }

    // RF03: teacher approvals per year
    public Map<Integer, Boolean> getAllYearsTeachers() {
        return allYearsTeachers;
    }

    public void setTeacherApprovalForYear(int year, boolean approved) {
        allYearsTeachers.put(year, approved);
    }

    // convenience: sum of weights (validation helper)
    public double totalWeight() {
        return evaluations.stream().mapToDouble(Evaluation::getWeight).sum();
    }
}
