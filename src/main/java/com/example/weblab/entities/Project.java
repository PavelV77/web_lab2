package com.example.weblab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@Table
@NoArgsConstructor
public class Project {
    @Id
    private long id;
    @Column
    private String name;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column
    private long startDate;

    //@JsonIgnore
    @Column
    private long endDate;

    /*public Date  getEnd(){
        return new Date(TimeUnit.SECONDS.toMillis(endDate));
    }*/
}
