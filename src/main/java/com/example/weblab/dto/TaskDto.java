package com.example.weblab.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {
    private long id;
    private String name;
    private String description;
    private long endDate;
    private boolean completed;
    private long projectId;
}
