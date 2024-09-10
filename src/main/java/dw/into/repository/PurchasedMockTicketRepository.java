package dw.into.repository;

import dw.into.model.PurchasedMockTicket;
import dw.into.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasedMockTicketRepository extends JpaRepository<PurchasedMockTicket, Integer> {
    List<PurchasedMockTicket> findByUser(User user);
}
