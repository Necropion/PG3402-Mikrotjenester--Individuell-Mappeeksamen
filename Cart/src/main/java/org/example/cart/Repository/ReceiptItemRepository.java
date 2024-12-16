package org.example.cart.Repository;

import org.example.cart.Model.ReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptItemRepository extends JpaRepository<ReceiptItem, Long> {
    List<ReceiptItem> findByReceiptId(Long receipt_id);
}
