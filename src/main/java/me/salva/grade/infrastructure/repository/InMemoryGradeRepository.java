package me.salva.grade.infrastructure.repository;

import me.salva.grade.domain.model.Grade;
import me.salva.grade.domain.repository.GradeRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory repository used for testing / dev.
 */
public class InMemoryGradeRepository implements GradeRepository {

    private final Map<String, Grade> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<Grade> findById(String studentId) {
        return Optional.ofNullable(storage.get(studentId));
    }

    @Override
    public void save(Grade grade) {
        storage.put(grade.getStudentId(), grade);
    }
}
