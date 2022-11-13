package com.example.Excel.repository;

import com.example.Excel.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT s FROM Stock s WHERE s.id IN(:ids)")
    List<Stock> getByIds(@Param("ids") List<Long> ids);

    @Query(value = "select count(id) from stock", nativeQuery = true)
    Integer findAllValue();

    @Query(value = "select count(id) from stock where quantity <= 5", nativeQuery = true)
    Integer findTotal();
}
