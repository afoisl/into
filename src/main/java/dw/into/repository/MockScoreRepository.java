package dw.into.repository;

import dw.into.model.MockScore;
import dw.into.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MockScoreRepository extends JpaRepository<MockScore, Integer> {

    List<MockScore> findByUser(User user);
}