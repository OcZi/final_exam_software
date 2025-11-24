package me.salva.grade.domain.repository;

import me.salva.grade.domain.model.Grade;
import java.util.Optional;

/**
 * Domain port for persistence.
 */
public interface GradeRepository {
    Optional<Grade> findById(String studentId);
    void save(Grade grade);
}
