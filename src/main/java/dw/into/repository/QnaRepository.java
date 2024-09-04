package dw.into.repository;

import dw.into.model.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaRepository extends JpaRepository<QnA, Long> {
    Optional<QnA> findById(Long id);
}
