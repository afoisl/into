package dw.into.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    private int storeItemId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "store_item_id")
    private StoreItem storeItem;

    @Column
    private String bookName;

    @Column
    private int bookPrice;
}
