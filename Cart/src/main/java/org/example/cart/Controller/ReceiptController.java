package org.example.cart.Controller;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.cart.DTO.ReceiptItemRequestDTO;
import org.example.cart.EventDriven.DealershipEvent;
import org.example.cart.Model.Receipt;
import org.example.cart.Model.ReceiptItem;
import org.example.cart.Service.CartService;
import org.example.cart.Service.ProductService;
import org.example.cart.Service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/receipt")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;
    private final ProductService productService;

    // Receipt
    @GetMapping()
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @PostMapping()
    public Receipt createReceipt(@RequestBody Receipt receipt) {
        log.info("Create receipt: {}", receipt);

        return receiptService.addReceipt(receipt);
    }

    @GetMapping("/{userId}")
    public List<Receipt> getAllUserReceipts(@PathVariable Long userId) {
        log.info("Get all user receipts: {}", userId);

        return receiptService.getAllReceiptsByUserId(userId);
    }

    // Receipt Items
    @GetMapping("/items/{receiptId}")
    public List<ReceiptItem> getReceiptItems(@PathVariable Long receiptId) {
        log.info("Get receipt items: {}", receiptId);

        return receiptService.getOneReceiptItems(receiptId);
    }

    @PostMapping("/items")
    public ReceiptItem addReceiptItem(@RequestBody ReceiptItemRequestDTO receiptItemRequestDTO) {
        log.info("Add receipt item: {}", receiptItemRequestDTO);

        DealershipEvent itemStockChange = new DealershipEvent(receiptItemRequestDTO.getUserId(), receiptItemRequestDTO.getProductId(), receiptItemRequestDTO.getQuantity(), receiptItemRequestDTO.getReceiptId());

        productService.postProductStockChanges(itemStockChange);

        return receiptService.addOneReceiptItem(receiptItemRequestDTO);
    }
}
