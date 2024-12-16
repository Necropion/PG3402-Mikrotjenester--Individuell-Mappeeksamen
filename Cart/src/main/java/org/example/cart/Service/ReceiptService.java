package org.example.cart.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cart.DTO.ReceiptItemRequestDTO;
import org.example.cart.Model.Receipt;
import org.example.cart.Model.ReceiptItem;
import org.example.cart.Repository.ReceiptItemRepository;
import org.example.cart.Repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptItemRepository receiptItemRepository;

    // Receipt
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt addReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    // Receipt Items
    public List<ReceiptItem> getOneReceiptItems(Long receiptId) {
        return receiptItemRepository.findByReceiptId(receiptId);
    }

    public ReceiptItem addOneReceiptItem(ReceiptItemRequestDTO receiptItemRequestDTO) {

        ReceiptItem receiptItem = new ReceiptItem();

        Receipt receipt = receiptRepository.findById(receiptItemRequestDTO.getReceiptId())
                .orElseThrow(() -> new RuntimeException("Receipt not found"));

        receiptItem.setReceipt(receipt);
        receiptItem.setProductId(receiptItemRequestDTO.getProductId());
        receiptItem.setProductName(receiptItemRequestDTO.getProductName());
        receiptItem.setProductPrice(receiptItemRequestDTO.getProductPrice());
        receiptItem.setQuantity(receiptItemRequestDTO.getQuantity());
        receiptItem.setTotal(receiptItemRequestDTO.getProductPrice() * receiptItemRequestDTO.getQuantity());

        return receiptItemRepository.save(receiptItem);
    }
}
