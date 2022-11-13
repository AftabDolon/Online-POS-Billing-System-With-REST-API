package com.example.Excel.repository;

import com.example.Excel.entity.SalesProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesProductRepository extends JpaRepository<SalesProduct, Long> {
    @Query("SELECT s FROM SalesProduct s WHERE s.id IN(:ids)")
    List<SalesProduct> getByIds(@Param("ids") List<Long> ids);

}
