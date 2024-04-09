package com.example.weblab.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Entity
@Getter
@Table(name = "task", schema = "weblab")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id",columnDefinition = "int8")
    private Project projectId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private long endDate;
    @Column
    private boolean completed;
}
