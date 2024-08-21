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
    @JoinColumn
    private StoreItem storeItem;

    @Column
    private int ticketPrice;
}
