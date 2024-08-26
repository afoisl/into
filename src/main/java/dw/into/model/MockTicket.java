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
    private int storeItemId;

    @Column
    private int ticketPrice;

    @Column
    private String mockTicketName;
}
