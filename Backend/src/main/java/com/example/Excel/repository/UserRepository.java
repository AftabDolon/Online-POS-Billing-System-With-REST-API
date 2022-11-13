package com.example.Excel.repository;

import com.example.Excel.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.id IN(:ids)")
    List<Users> getByIds(@Param("ids") List<Long> ids);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.status = ?1 where u.id = ?2")
    int setStatusForUser(Boolean status, Long id);

    @Query(value = "select count(id) from users", nativeQuery = true)
    Integer findTotal();
}
