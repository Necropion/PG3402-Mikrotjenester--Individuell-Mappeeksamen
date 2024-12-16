package org.example.cart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receipt_id", nullable = false)
    @JsonIgnore
    private Receipt receipt;

    private Long productId;
    private String productName;
    private Double productPrice;
    private Long quantity;
    private Double total;
}
