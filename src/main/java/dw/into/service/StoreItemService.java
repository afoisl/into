package dw.into.service;

import dw.into.exception.ResourceNotFoundException;
import dw.into.model.Book;
import dw.into.model.MockTicket;
import dw.into.model.StoreItem;
import dw.into.repository.BookRepository;
import dw.into.repository.MockTicketRepository;
import dw.into.repository.StoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreItemService {
    @Autowired
    StoreItemRepository storeItemRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    MockTicketRepository mockTicketRepository;
    public List<StoreItem> getAllStoreItems() {
        return storeItemRepository.findAll();
    }

    public StoreItem getStoreItemById(int id) {
        return storeItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StoreItem", "ID", id));
    }

    public List<Book> getBooksByStoreItemId(int storeItemId) {
        StoreItem storeItem = getStoreItemById(storeItemId);
        return bookRepository.findByStoreItemId(storeItem.getStoreItemId());
    }

    public List<MockTicket> getMockTicketsByStoreItemId(int storeItemId) {
        StoreItem storeItem = getStoreItemById(storeItemId);
        return mockTicketRepository.findByStoreItemId(storeItem.getStoreItemId());
    }
}
