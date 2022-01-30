package com.example.demo.model;

import com.example.demo.repository.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    public List<User> results;
}
