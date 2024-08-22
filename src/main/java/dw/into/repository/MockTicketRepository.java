package dw.into.repository;

import dw.into.model.MockTicket;
import dw.into.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MockTicketRepository extends JpaRepository<MockTicket, Integer> {
    List<MockTicket> findByStoreItemId(int id);
}