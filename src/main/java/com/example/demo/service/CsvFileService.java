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
import java.util.List;

@Service
@AllArgsConstructor
public class CsvFileService {

    @Autowired
    private final UserRepo repo = null;

    public Boolean loadUserData(MultipartFile file) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        CSVParser parser = new CSVParser();

        new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .skip(1)
                .forEach(line -> {
                    String[] params = new String[0];
                    try {
                        params = parser.parseLine(line);
                    } catch (IOException e) {
                        System.out.println("Ex Message: " + e.getMessage());
                        System.out.println("Ex Cause: " + e.getCause());
                        e.printStackTrace();
                    }
                    users.add(new User(params[0], Double.parseDouble(params[1])));
                });

        saveUsers(users);

        return true;
    }

    private void saveUsers(List<User> users) {

        int batchSize = 5000;

        for (int i = 0; i < users.size() / batchSize + 1; i++) {
            int fromIndex = i * batchSize;
            int toIndex = Math.min(i * batchSize + batchSize, users.size());

            List<User> sublist = users.subList(fromIndex, toIndex);

            repo.saveAllAndFlush(sublist);
        }
    }
}
