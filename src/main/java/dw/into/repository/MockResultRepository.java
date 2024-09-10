package dw.into.repository;

import dw.into.model.Mock;
import dw.into.model.MockResult;
import dw.into.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MockResultRepository extends JpaRepository<MockResult, Long> {
    List<MockResult> findByUser(User user);
    Optional<MockResult> findByUserAndMock(User user, Mock mock);
}