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
@Table(name = "mock_ticket")
public class MockTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeItemId;

    private boolean isUsed = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mock_id")
    private Mock mock; // 관련된 시험 정보

    @Column
    private int ticketPrice;

    @Column
    private String mockTicketName;
}
