package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    @Query(value = "SELECT name, salary FROM user_salary " +
            "WHERE salary > :min AND salary < :max " +
            "ORDER BY :sort ASC LIMIT :limit OFFSET :offset ",
            nativeQuery = true
            )
    List<User> getUsers(@Param("min") Double min,
                        @Param("max") Double max,
                        @Param("offset") Integer offset,
                        @Param("limit") Integer limit,
                        @Param("sort") String sort);
}

