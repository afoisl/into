package dw.into.service;

import dw.into.dto.PurchaseResponseDto;
import dw.into.model.*;
import dw.into.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final StoreItemRepository storeItemRepository;
    private final LectureRepository lectureRepository;
    private final BookRepository bookRepository;
    private  final MockTicketRepository mockTicketRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository,
                           UserRepository userRepository,
                           StoreItemRepository storeItemRepository,
                           LectureRepository lectureRepository,
                           BookRepository bookRepository,
                           MockTicketRepository mockTicketRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.storeItemRepository = storeItemRepository;
        this.lectureRepository = lectureRepository;
        this.bookRepository = bookRepository;
        this.mockTicketRepository = mockTicketRepository;
    }

    @Transactional
    public Purchase savePurchase(Purchase purchase) {
        User user = userRepository.findById(purchase.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("user not found"));
        purchase.setUser(user);

        StoreItem storeItem = storeItemRepository.findById(purchase.getStoreItem().getStoreItemId())
                .orElseThrow(() -> new RuntimeException("storeItem not found"));
        purchase.setStoreItem(storeItem);
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    public List<PurchaseResponseDto> getAllPurchasesWithLecture() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream()
                .map(this::convertToPurchaseResponseDto)
                .collect(Collectors.toList());
    }

    private PurchaseResponseDto convertToPurchaseResponseDto(Purchase purchase) {
        PurchaseResponseDto dto = new PurchaseResponseDto();
        dto.setPurchaseId(purchase.getPurchaseId());
        dto.setUser(purchase.getUser());
        dto.setStoreItem(purchase.getStoreItem());
        dto.setPurchaseTime(purchase.getPurchaseTime());
        dto.setAddress(purchase.getAddress());

        Integer storeItemId = purchase.getStoreItem().getStoreItemId();


        List<Lecture> lectures = lectureRepository.findByStoreItemId(storeItemId);
        dto.setLectures(lectures);

        List<Book> books = bookRepository.findByStoreItemId(storeItemId);
        dto.setBooks(books);

        List<MockTicket> mockTickets = mockTicketRepository.findByStoreItemId(storeItemId);
        dto.setMockTickets(mockTickets);

        return dto;
    }

}