package com.example.backend.model;

import lombok.Data;

@Data
public class SearchProductRequest {

    private int page_number;
    private int page_size;
    private String sort_by;
    private String searchValue;

}
