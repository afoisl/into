package dw.into.repository;

import dw.into.model.MockGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MockGradeRepository extends JpaRepository<MockGrade, String> {
    Optional<MockGrade> findByHighLimitGreaterThanEqualAndLowLimitLessThanEqual(int score, int score2);
}