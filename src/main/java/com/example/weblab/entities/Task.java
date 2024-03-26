package com.example.weblab.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
public class Task {
    private long id;
    private String name;
    private String description;
    private long endDate;
    private boolean completed;

}
