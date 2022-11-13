package com.example.Excel.serviceImplementation;

import com.example.Excel.entity.Users;
import com.example.Excel.repository.UserRepository;
import com.example.Excel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Users update(Users users) throws Exception {
        if (users.hasId()) {
            return save(users);
        } else {
            throw new InvalidDnDOperationException("User id required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        userRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public List<Users> getDataByIds(Long... ids) {
        return userRepository.getByIds(Arrays.asList(ids));
    }

    @Override
    public List<Users> getData() {
        return userRepository.findAll();
    }

    @Override
    public int updateStatusUser(Boolean status, Long id) {
        return userRepository.setStatusForUser(status, id);

    }

    @Override
    public Integer findTotal() {
        return userRepository.findTotal();
    }
}
