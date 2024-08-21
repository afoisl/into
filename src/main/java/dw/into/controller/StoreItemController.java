package dw.into.controller;

import dw.into.model.Book;
import dw.into.model.MockTicket;
import dw.into.model.StoreItem;
import dw.into.service.StoreItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreItemController {
    StoreItemService storeItemService;

    @Autowired
    public StoreItemController(StoreItemService storeItemService) {this.storeItemService = storeItemService;}

    @GetMapping("/storeItem")
    public ResponseEntity<List<StoreItem>> getAllStoreItems() {
        return new ResponseEntity<>(storeItemService.getAllStoreItems(), HttpStatus.OK);
    }

    @GetMapping("/storeItem/{id}")
    public ResponseEntity<StoreItem> getStoreItemById(@PathVariable int id) {
        return new ResponseEntity<>(storeItemService.getStoreItemById(id),
                HttpStatus.OK);
    }

    @GetMapping("/storeItem/{id}/books")
    public ResponseEntity<List<Book>> getBooksByStoreItemId(@PathVariable int id) {
        return new ResponseEntity<>(storeItemService.getBooksByStoreItemId(id),
                HttpStatus.OK);
    }

    @GetMapping("/storeItem/{id}/mockTickets")
    public ResponseEntity<List<MockTicket>> getMockTicketsByStoreItemId(@PathVariable int id) {
        return new ResponseEntity<>(storeItemService.getMockTicketsByStoreItemId(id),
                HttpStatus.OK);
    }
}
