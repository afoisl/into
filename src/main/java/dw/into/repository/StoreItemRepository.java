package dw.into.repository;

import dw.into.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreItemRepository extends JpaRepository<StoreItem,Integer> {
}