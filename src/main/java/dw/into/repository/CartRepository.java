package dw.into.repository;

import dw.into.model.Purchase;
import dw.into.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUser(User user);
}
