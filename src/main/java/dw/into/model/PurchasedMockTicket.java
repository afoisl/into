package dw.into.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "purchased_mock_ticket")
public class PurchasedMockTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}