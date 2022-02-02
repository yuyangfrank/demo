package com.example.demo.service;

import com.example.demo.repository.User;
import com.example.demo.repository.UserRepo;
import com.opencsv.CSVParser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CsvFileService {

    @Autowired
    private final UserRepo repo = null;

    public Boolean loadUserData(MultipartFile file) throws IOException {
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

        return true;

    }
}
