package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class RequestError {
    public String error;
}
