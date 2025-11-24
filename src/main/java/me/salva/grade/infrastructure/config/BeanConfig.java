package me.salva.software.infrastructure.config;

import me.salva.software.application.usecase.CalculateFinalGradeUseCase;
import me.salva.software.domain.service.GradeCalculatorDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {


    @Bean
    public GradeCalculatorDomainService domainService() {
        return new GradeCalculatorDomainService();
    }


    @Bean
    public CalculateFinalGradeUseCase calculateFinalGradeUseCase(GradeCalculatorDomainService domainService) {
        return new CalculateFinalGradeUseCase(domainService);
    }
}
