package dw.into.repository;

import dw.into.model.Book;
import dw.into.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByStoreItemId(int id);
}