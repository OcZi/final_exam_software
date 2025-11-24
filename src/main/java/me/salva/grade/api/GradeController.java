package me.salva.grade.api;

import me.salva.grade.application.dto.GradeRequest;
import me.salva.grade.application.dto.GradeResponse;
import me.salva.grade.application.usecase.CalculateFinalGradeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final CalculateFinalGradeUseCase useCase;

    public GradeController(CalculateFinalGradeUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{studentId}/calculate")
    public ResponseEntity<GradeResponse> calculate(@PathVariable String studentId) {
        GradeResponse resp = useCase.execute(new GradeRequest(studentId));
        return ResponseEntity.ok(resp);
    }
}
