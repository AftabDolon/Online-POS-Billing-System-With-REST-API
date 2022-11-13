package com.example.Excel.repository;

import com.example.Excel.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.id IN(:ids)")
    List<Product> getByIds(@Param("ids") List<Long> ids);

    @Query(value = "select id, product_id, product_name, expire_date, original_price, qty_left from products", nativeQuery = true)
    Object[][] getProductIds();

    @Query(value = "select count(id) from products", nativeQuery = true)
    Integer findAllValue();

    @Query(value = "select count(id) from products where quantity <= 5", nativeQuery = true)
    Integer findTotal();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product p SET p.quantity = :qty WHERE p.Id = :productId", nativeQuery = true)
    int updateProductQty(@Param("qty") Integer qty, @Param("productId") Long productId);


    @Transactional
    @Modifying
    @Query(value = "update products set qty_left=qty_left-:left where id =:ids", nativeQuery = true)
    int updateQtyLeft(@Param("left") Integer left, @Param("ids") Long ids);

}
