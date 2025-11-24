package me.salva.grade.application.usecase;

import me.salva.grade.application.dto.GradeRequest;
import me.salva.grade.application.dto.GradeResponse;
import me.salva.grade.domain.model.Evaluation;
import me.salva.grade.domain.model.Grade;
import me.salva.grade.domain.repository.GradeRepository;
import me.salva.grade.domain.service.GradeCalculatorDomainService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CalculateFinalGradeUseCaseTest {

    @Test
    void shouldCalculateFinalGradeCorrectly() {
        GradeRepository repo = Mockito.mock(GradeRepository.class);
        Grade grade = new Grade("student-1");
        grade.addEvaluation(new Evaluation(18.0, 0.3));
        grade.addEvaluation(new Evaluation(15.0, 0.7));
        grade.setHasReachedMinimumClasses(true);
        grade.setTeacherApprovalForYear(2024, true);

        Mockito.when(repo.findById("student-1")).thenReturn(Optional.of(grade));

        var useCase = new CalculateFinalGradeUseCase(repo, new GradeCalculatorDomainService());
        GradeResponse resp = useCase.execute(new GradeRequest("student-1"));

        assertEquals(16.0, resp.finalGrade());
        assertFalse(resp.appliedAttendancePenalty());
        assertTrue(resp.appliedExtraPoints());
    }

    @Test
    void shouldReturnZeroWhenNoAttendance() {
        GradeRepository repo = Mockito.mock(GradeRepository.class);
        Grade grade = new Grade("s2");
        grade.addEvaluation(new Evaluation(18.0, 0.3));
        grade.addEvaluation(new Evaluation(15.0, 0.7));
        grade.setHasReachedMinimumClasses(false);

        Mockito.when(repo.findById("s2")).thenReturn(Optional.of(grade));

        var useCase = new CalculateFinalGradeUseCase(repo, new GradeCalculatorDomainService());
        GradeResponse resp = useCase.execute(new GradeRequest("s2"));

        assertEquals(0.0, resp.finalGrade());
        assertTrue(resp.appliedAttendancePenalty());
        assertFalse(resp.appliedExtraPoints());
    }
}
