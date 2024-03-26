package com.example.weblab.repositories.impl;

import com.example.weblab.entities.Project;
import com.example.weblab.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Slf4j
public class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Project createProject(Project project) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", project.getName())
                .addValue("startDate", project.getStartDate())
                .addValue("endDate", project.getEndDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update("INSERT INTO \"weblab\".\"project\"(name,start_date,end_date) VALUES(:name,:startDate,:endDate)",
                params, keyHolder, new String[]{"id"});
        return findProjectById(keyHolder.getKey().longValue());
    }

    public Project findProjectById(long id){
        return (Project) jdbcTemplate.queryForObject("SELECT * FROM weblab.project p where p.id = ?",
                (resultSet, rowNum) -> {
                    Project retProject = new Project();
                    retProject.setId(resultSet.getLong("id"));
                    retProject.setName(resultSet.getString("name"));
                    retProject.setStartDate(resultSet.getLong("start_date"));
                    retProject.setEndDate(resultSet.getLong("end_date"));
                    return retProject;
                },id);
    }

    @Override
    public void updateProject(Project project) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id",project.getId())
                .addValue("name", project.getName())
                .addValue("startDate", project.getStartDate())
                .addValue("endDate", project.getEndDate());
        namedParameterJdbcTemplate.update("UPDATE weblab.project " +
                "SET name = :name, start_date = :startDate, end_date = :endDate" +
                " where id = :id", params);

    }

    public void deleteProject(long id){
        jdbcTemplate.update("DELETE FROM weblab.project where id = ?", id);
    }

    @Override
    public List<Project> getAll() {
        return jdbcTemplate.query("SELECT * FROM weblab.project",
                (resultSet, rowNum) -> {
                    Project retProject = new Project();
                    retProject.setId(resultSet.getLong("id"));
                    retProject.setName(resultSet.getString("name"));
                    retProject.setStartDate(resultSet.getLong("start_date"));
                    retProject.setEndDate(resultSet.getLong("end_date"));
                    return retProject;
                });
    }

    @Override
    public List<Project> getAllWithFilter(long startDate, long endDate) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("startDate",startDate)
                .addValue("endDate", endDate);
        return namedParameterJdbcTemplate.query("SELECT * FROM weblab.project " +
                        "where start_date > :startDate and start_date < :endDate " +
                        "and end_date > :startDate and end_date < :endDate", params,
                (resultSet, rowNum) -> {
                    Project retProject = new Project();
                    retProject.setId(resultSet.getLong("id"));
                    retProject.setName(resultSet.getString("name"));
                    retProject.setStartDate(resultSet.getLong("start_date"));
                    retProject.setEndDate(resultSet.getLong("end_date"));
                    return retProject;
                });
    }
}
