package me.salva.software.api;

import me.salva.software.application.dto.GradeRequest;
import me.salva.software.application.dto.GradeResponse;
import me.salva.software.application.usecase.CalculateFinalGradeUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
public class GradeController {
    private final CalculateFinalGradeUseCase useCase;

    public GradeController(CalculateFinalGradeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/calculate")
    public GradeResponse calculate(@RequestBody GradeRequest request) {
        return useCase.execute(request);
    }
}