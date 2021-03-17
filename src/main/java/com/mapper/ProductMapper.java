package com.mapper;

import com.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface ProductMapper {
    /***
     * 获取产品
     * for update 通过数据库内部的锁对记录进行加锁
     * @Select("select * from product where id=#{id} for update")
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product getById(@Param("id") Long id);

    /**
     * 更新产品减少库存
     * @param id
     * @param quantity
     * @return
     */
    @Update("update product set stock=stock - #{quantity},version=version+1 where id=#{id} and version=#{version}")
    int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity,@Param("version") int version);
}
