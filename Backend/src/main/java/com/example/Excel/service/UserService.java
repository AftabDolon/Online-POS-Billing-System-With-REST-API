package com.example.Excel.service;

import com.example.Excel.entity.Users;

public interface UserService extends BaseService<Users, Long> {

    int updateStatusUser(Boolean status, Long id);

    Integer findTotal();
}
