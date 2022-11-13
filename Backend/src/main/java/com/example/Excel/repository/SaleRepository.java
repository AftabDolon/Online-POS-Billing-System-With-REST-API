package com.example.Excel.repository;

import com.example.Excel.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sales, Long> {
    @Query("SELECT s FROM Sales s WHERE s.id IN(:ids)")
    List<Sales> getByIds(@Param("ids") List<Long> ids);

    @Query(value = "SELECT @@innodb_autoinc_lock_mode", nativeQuery = true)
    String getLastInsertId();

    @Query(value = "select sum(total_amount) from sales where date(transaction_date) = curdate()", nativeQuery = true)
    int findTotalAmount();

    @Query("SELECT s FROM Sales s WHERE DATE(s.transactionDate) between DATE(:start) and DATE(:end)")
    List<Sales> getAllBetweenDates(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select sum(total_amount) from sales where date(transaction_date) between date(:start) and date(:end)", nativeQuery = true)
    List<Sales> getTotalBetweenDates(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select s.transaction_date, s.customer_name, s.invoice_number, s.total_amount, p.product_name, p.original_price \n" +
            " from sales s \n" +
            " join tbl_sales_product tsp on s.id = tsp.sale_id join sale_product sp on tsp.product_id = sp.id AND DATE(s.transaction_date) between DATE(:start) and DATE(:end) \n" +
            " join products p on sp.product_id = p.id AND p.id IN(:ids)", nativeQuery = true)
    List<Tuple> getByIdsBetween(@Param("ids") List<Long> ids, @Param("start") Date start, @Param("end") Date end);

    @Query(value = "select * from sales where date(transaction_date) = curdate()", nativeQuery = true)
    List<Sales> findTotalSale();

    @Query(value = "select s.invoice_number, s.transaction_date,\n" +
            " p.product_id, s.customer_name, sp.sold_quantity, s.total_amount, p.original_price from sales s\n" +
            " join tbl_sales_product tsp on s.id = tsp.sale_id join sale_product sp\n" +
            " on tsp.product_id = sp.id join products p on sp.product_id = p.id;", nativeQuery = true)
    Object[][] findSale();

    @Query(value = "select s.invoice_number, s.transaction_date,\n" +
            " p.product_id, s.customer_name, sp.sold_quantity, s.total_amount, p.original_price from sales s\n" +
            " join tbl_sales_product tsp on s.id = tsp.sale_id join sale_product sp\n" +
            " on tsp.product_id = sp.id join products p on sp.product_id = p.id and date(s.transaction_date) = curdate();", nativeQuery = true)
    Object[][] findSaleByDate();

}
