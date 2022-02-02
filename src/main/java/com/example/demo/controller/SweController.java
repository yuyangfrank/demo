package com.example.demo.controller;


import com.example.demo.model.Result;
import com.example.demo.repository.User;
import com.example.demo.repository.UserRepo;
import com.opencsv.CSVParser;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
        return new Result(repo.getUsers(minSal, maxSal, offset, limit, sort));
    }

    @PostMapping(path = "/upload")
    public void upload(@RequestPart("file") MultipartFile file) throws IOException {
        System.out.println("===========================================");

        ArrayList<User> users = new ArrayList<>();
        CSVParser parser = new CSVParser();

        new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                .lines().forEach(line -> {
                    String[] params = new String[0];
                    try {
                        params = parser.parseLine(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    users.add(new User(params[0], Double.parseDouble(params[1])));
                });

        repo.saveAllAndFlush(users);
    }
}
