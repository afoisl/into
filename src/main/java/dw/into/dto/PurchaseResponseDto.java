package dw.into.dto;

import dw.into.model.*;

import java.time.LocalDateTime;

public class PurchaseResponseDto {
    private long purchaseId;
    private User user;
    private StoreItem storeItem;
    private LocalDateTime purchaseTime;
    private String address;
    private Lecture lecture;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public MockTicket getMockTicket() {
        return mockTicket;
    }

    public void setMockTicket(MockTicket mockTicket) {
        this.mockTicket = mockTicket;
    }

    private Book book;
    private MockTicket mockTicket;

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StoreItem getStoreItem() {
        return storeItem;
    }

    public void setStoreItem(StoreItem storeItem) {
        this.storeItem = storeItem;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }




}
