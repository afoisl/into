package dw.into.repository;

import dw.into.model.Mock;
import dw.into.model.MockQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MockQuestionRepository extends JpaRepository<MockQuestion, Long> {
    List<MockQuestion> findByMock(Mock mock);
}