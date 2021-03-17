package com.service;

import com.entity.Product;
import com.entity.PurchaseRecord;
import com.mapper.ProductMapper;
import com.mapper.PurchaseRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    PurchaseRecordMapper purchaseRecordMapper;

    //购买业务:使用乐观锁一定时间内进行处理,超过时间则不再进行请求重试
    @Override
    @Transactional
    public boolean purchase(Long userId, Long productId, int quantity) {
        long start = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start > 100) {
                return false;
            }
            Product product = productMapper.getById(productId);
            // 比较库存和购买数量
            if (product.getStock() < quantity) {
                return false;
            }
            //获取当前版本号
            int version = product.getVersion();
            // 扣减库存
            int result = productMapper.decreaseProduct(productId, quantity, version);
            if (result == 0) {
                return false;
            }

            // 初始化购买记录
            PurchaseRecord pr = this.initPurchaseRecord(userId, product, quantity);
            purchaseRecordMapper.insertPurchaseRecord(pr);
            return true;
        }
    }

    // 初始化购买信息
    private PurchaseRecord initPurchaseRecord(Long userId, Product product, int quantity) {
        PurchaseRecord pr = new PurchaseRecord();
        pr.setNote("购买日志，时间：" + System.currentTimeMillis());
        pr.setPrice(product.getPrice());
        pr.setProductId(product.getId());
        pr.setQuantity(quantity);
        double sum = product.getPrice() * quantity;
        pr.setSum(sum);
        pr.setUserId(userId);
        return pr;
    }

    @Override
    public boolean purchaseRedis(Long userId, Long productId, int quantity) {
        return false;
    }

    @Override
    public boolean dealRedisPurchase(List<PurchaseRecord> prpList) {
        return false;
    }
}
