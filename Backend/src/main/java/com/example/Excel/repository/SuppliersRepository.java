package com.example.Excel.repository;

import com.example.Excel.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {

    @Query("SELECT s FROM Suppliers s WHERE s.id IN(:ids)")
    List<Suppliers> getByIds(@Param("ids") List<Long> ids);

    @Query(value = "select id, supplier_name from suppliers", nativeQuery = true)
    Object[][] getSupplierIds();

    @Query(value = "select count(id) from suppliers", nativeQuery = true)
    Integer findAllValue();
}
