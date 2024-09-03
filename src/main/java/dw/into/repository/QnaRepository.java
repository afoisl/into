package dw.into.repository;

import dw.into.model.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnA, Long> {
}
