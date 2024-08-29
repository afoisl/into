package dw.into.controller;

import dw.into.model.Lecture;
import dw.into.model.Purchase;
import dw.into.model.User;
import dw.into.service.PurchaseService;
import dw.into.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {this.purchaseService = purchaseService;}

    @PostMapping("/save")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.savePurchase(purchase);
        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchase() {
        return new ResponseEntity<>(purchaseService.getAllPurchase(),
                HttpStatus.OK);
    }




}
