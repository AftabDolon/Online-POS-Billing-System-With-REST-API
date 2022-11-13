package com.example.Excel.controller;

import com.example.Excel.entity.Users;
import com.example.Excel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController implements BaseController<Users, Long> {

    @Autowired
    private UserService userService;

    //all information save purpose
    @Override
    public ResponseEntity<Users> save(@RequestBody Users users) {
        userService.save(users);
        return ResponseEntity.ok(users);
    }

    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Users users) throws Exception {
        try {
            userService.update(users);
            return ResponseEntity.ok("Successfully user information has been update");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    //single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        userService.deleteByIds(ids);
        return ResponseEntity.ok("ID: " + Arrays.toString(ids) + " has been deleted successfully");
    }

    //single information get purpose
    @Override
    public ResponseEntity<List<Users>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Users> usersList = userService.getDataByIds(ids);
        return ResponseEntity.ok(usersList);
    }

    //all information get purpose
    @Override
    public List<Users> getData() {
        return userService.getData();
    }

    //user access provide purpose
    @PutMapping("status-update/{id}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Boolean status) {
        userService.updateStatusUser(status, id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("countAll")
    public Integer getTotalUser() {
        return userService.findTotal();
    }
}
