package dw.into.service;

import dw.into.dto.PurchaseResponseDto;
import dw.into.model.Lecture;
import dw.into.model.Purchase;
import dw.into.model.StoreItem;
import dw.into.model.User;
import dw.into.repository.LectureRepository;
import dw.into.repository.PurchaseRepository;
import dw.into.repository.StoreItemRepository;
import dw.into.repository.UserRepository;
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

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository,
                           UserRepository userRepository,
                           StoreItemRepository storeItemRepository,
                           LectureRepository lectureRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.storeItemRepository = storeItemRepository;
        this.lectureRepository = lectureRepository;
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

        Lecture lecture = (Lecture) lectureRepository.findByStoreItemId(purchase.getStoreItem().getStoreItemId());
        if (lecture != null) {
            dto.setLecture(lecture);
        }
        return dto;
    }
}