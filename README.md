## 高并发抢购商品
超发现象：商品库存被多个线程所修改，无法保证执行的顺序     
- 悲观锁       
通过锁限制别的线程进行读写操作，直到当前事务完成才释放这条数据的锁
```select * from product where id=#{id} for update```
![悲观锁](https://github.com/iiFeng/ProductBuying/blob/master/src/main/resources/悲观锁.png?raw=true)

- 乐观锁       
乐观锁不使用数据库锁和不阻塞线程并发的方案。      
更新共享值如此处的商品库存，线程开始之前记录一个版本号，版本号只增不减，该线程结束前如共享值未发生改变即没有别的线程事务修改过数据。如共享值被修改则该业务更新数据失败。
乐观锁中若业务更新失败还会不断重试，可以通过时间戳限制重入的乐观锁或者限定次数重入     
      
![乐观锁](https://github.com/iiFeng/ProductBuying/blob/master/src/main/resources/乐观锁.png?raw=true)
