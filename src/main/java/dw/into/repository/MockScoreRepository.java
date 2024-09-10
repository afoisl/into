package dw.into.repository;

import dw.into.model.MockScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockScoreRepository extends JpaRepository<MockScore, Integer> {
}
