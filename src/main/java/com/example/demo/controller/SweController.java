package com.example.demo.controller;


import com.example.demo.model.Result;
import com.example.demo.repository.User;
import com.example.demo.repository.UserRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class SweController {

    @Autowired
    private final UserRepo repo = null;

    @GetMapping(path = "/users")
    public Result getUsers(
            @RequestParam(name = "min", defaultValue = "0.0") Double minSal,
            @RequestParam(name = "max", defaultValue = "4000.0") Double maxSal,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "limit", defaultValue = "1000") Integer limit,
            @RequestParam(name = "sort", defaultValue = "") String sort
    ) {
        try {
             repo.getUsers(minSal, maxSal, offset, limit, sort);

            return new Result(repo.getUsers(minSal, maxSal, offset, limit, sort));
        } catch (Exception e) {
            return new Result(e.getMessage());
        }
    }
}