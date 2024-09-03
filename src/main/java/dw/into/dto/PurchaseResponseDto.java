package dw.into.dto;

import dw.into.model.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class PurchaseResponseDto {
    private long purchaseId;
    private User user;
    private StoreItem storeItem;
    private LocalDateTime purchaseTime;
    private String address;
    private List<Lecture> lectures;
    private List<Book> books;
    private List<MockTicket> mockTickets;
}