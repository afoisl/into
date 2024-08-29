package dw.into.service;

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

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private final PurchaseRepository purchaseRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired

    private final StoreItemRepository storeItemRepository;
    @Autowired
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
}
