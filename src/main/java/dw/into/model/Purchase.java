package dw.into.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn
    private StoreItem storeItem;

    @Column(name="purchase_time")
    private LocalDateTime purchaseTime;

    @Column
    private String address;

    public Purchase(StoreItem storeItem, User user, LocalDateTime purchaseTime) {
        this.storeItem = storeItem;
        this.user = user;
        this.purchaseTime = purchaseTime;
    }
}
