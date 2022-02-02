package com.example.demo.controller;


import com.example.demo.service.CsvFileService;
import com.example.demo.model.Result;
import com.example.demo.model.UploadResult;
import com.example.demo.repository.UserRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class SweController {

    @Autowired
    private final UserRepo repo = null;

    @Autowired
    private final CsvFileService fileService = null;

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
    public UploadResult upload(@RequestPart("file") MultipartFile file) {
        System.out.println("===========================================");

        try {
            fileService.loadUserData(file);
        } catch (Exception ex) {
            System.out.println("File processing error: " + ex.getMessage());
            return new UploadResult(0);
        }

        return new UploadResult(1);
    }
}
