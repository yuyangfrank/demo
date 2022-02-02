package com.example.demo.service;

import com.example.demo.repository.User;
import com.example.demo.repository.UserRepo;
import com.opencsv.CSVParser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class CsvFileService {

    @Autowired
    private final UserRepo repo = null;

    public Boolean loadUserData(MultipartFile file) throws RuntimeException {
        AtomicBoolean fileErr = new AtomicBoolean(false);
        ArrayList<User> users = new ArrayList<>();
        CSVParser parser = new CSVParser();

        try {
            new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .skip(1)
                    .forEach(line -> {

                        if(line.equals("")) return;

                        String[] params = new String[0];
                        try {
                            params = parser.parseLine(line);
                        } catch (IOException e) {
                            System.out.println("=======>"+ e.getCause());
                            System.out.println("Line: " + line);
//                            e.printStackTrace();
                            fileErr.set(true);
//                            throw new RuntimeException(e);
                        }

                        if (params.length != 2) {
                            System.out.println("=====> Invliad column count.");
                            System.out.println("Line: " + line);
                            fileErr.set(true);
//                            throw new RuntimeException("Invliad column count. Line: " + line);
                        }
                        try {
                            users.add(new User(params[0], Double.parseDouble(params[1])));
                        } catch (Exception ex) {
                            System.out.println("=====> Invliad number.");
                            System.out.println("Line: " + line);
                            fileErr.set(true);
                        }
                    });

        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("file error when getInputStream()");
            fileErr.set(true);
//            throw new RuntimeException(e);
        }

        if(fileErr.get()) {
            throw new RuntimeException("File processing failed.");
        }

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
