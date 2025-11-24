package me.salva.grade.application.usecase;

import me.salva.grade.application.dto.RegisterEvaluationRequest;
import me.salva.grade.domain.model.Evaluation;
import me.salva.grade.domain.repository.GradeRepository;

public class RegisterEvaluationUseCase {

    private final GradeRepository repository;

    public RegisterEvaluationUseCase(GradeRepository repository) {
        this.repository = repository;
    }

    public void execute(RegisterEvaluationRequest request) {
        var grade = repository.findById(request.studentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + request.studentId()));

        // Business rule: max 10 evaluations (RNF01)
        if (grade.getEvaluations().size() >= 10) {
            throw new IllegalStateException("Max evaluations exceeded (10)");
        }

        grade.addEvaluation(new Evaluation(request.grade(), request.weight()));
        repository.save(grade);
    }
}
