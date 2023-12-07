package com.programmingtechie.inventoryservice.Service;

import com.programmingtechie.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String squCode) {
        return inventoryRepository.findBySkuCode(squCode).isPresent();
    }
}
