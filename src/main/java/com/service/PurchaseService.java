package com.service;

import com.entity.PurchaseRecord;

import java.util.List;

public interface PurchaseService {
    boolean purchase(Long userId, Long productId, int quantity);
    boolean purchaseRedis(Long userId, Long productId, int quantity);

    boolean dealRedisPurchase(List<PurchaseRecord> prpList);
}
