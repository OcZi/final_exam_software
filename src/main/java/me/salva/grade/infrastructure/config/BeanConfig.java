package me.salva.grade.infrastructure.config;

import me.salva.grade.domain.repository.GradeRepository;
import me.salva.grade.domain.service.GradeCalculatorDomainService;
import me.salva.grade.application.usecase.CalculateFinalGradeUseCase;
import me.salva.grade.infrastructure.repository.InMemoryGradeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public GradeRepository gradeRepository() {
        return new InMemoryGradeRepository();
    }

    @Bean
    public GradeCalculatorDomainService gradeCalculatorDomainService() {
        return new GradeCalculatorDomainService();
    }

    @Bean
    public CalculateFinalGradeUseCase calculateFinalGradeUseCase(GradeRepository repository,
                                                                 GradeCalculatorDomainService domainService) {
        return new CalculateFinalGradeUseCase(repository, domainService);
    }
}
