package com.example.weblab.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDto {
    private long id;
    private String name;
    private String description;
    private long startDate;
    private long endDate;
}

