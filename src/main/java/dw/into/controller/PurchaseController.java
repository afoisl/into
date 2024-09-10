package dw.into.controller;

import dw.into.dto.PurchaseResponseDto;
import dw.into.model.Lecture;
import dw.into.model.Purchase;
import dw.into.model.User;
import dw.into.repository.LectureRepository;
import dw.into.repository.PurchaseRepository;
import dw.into.service.PurchaseService;
import dw.into.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {


    private final PurchaseService purchaseService;
    private final UserService userService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, UserService userService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.savePurchase(purchase);
        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PurchaseResponseDto> getAllPurchasesWithLecture() {
        return purchaseService.getAllPurchasesWithLecture();
    }

    @GetMapping("/simple")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchase();
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseResponseDto>> getUserPurchases(@PathVariable String userId) {
        List<PurchaseResponseDto> purchases = purchaseService.getPurchasesByUser(userId);
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("/check/{userId}/{storeItemId}")
    public ResponseEntity<Boolean> checkLecturePurchased(@PathVariable String userId, @PathVariable int storeItemId) {
        boolean isPurchased = purchaseService.isLecturePurchasedByUser(userId, storeItemId);
        return ResponseEntity.ok(isPurchased);
    }


    @GetMapping("/user/{userId}/lectures")
    public ResponseEntity<List<Lecture>> getPurchasedLectures(@PathVariable String userId) {
        List<Lecture> purchasedLectures = purchaseService.getPurchasedLecturesByUser(userId);
        return ResponseEntity.ok(purchasedLectures);
    }

}
