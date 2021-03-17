package com.mapper;

import com.entity.PurchaseRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PurchaseRecordMapper {
    @Insert("insert into purchase_record(user_id, product_id, price, quantity, sum, purchase_date, note) values(#{purchaseRecord.userId}, #{purchaseRecord.productId},#{purchaseRecord.price}, #{purchaseRecord.quantity},#{purchaseRecord.sum}, now(), #{purchaseRecord.note})")
    void insertPurchaseRecord(@Param("purchaseRecord")PurchaseRecord purchaseRecord);
}
