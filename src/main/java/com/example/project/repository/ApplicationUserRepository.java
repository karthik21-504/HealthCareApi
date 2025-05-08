package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.Model.ApplicationUser;


@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String>{
   @Query("SELECT a FROM ApplicationUser a WHERE a.user_name = :user_name")
    Optional<ApplicationUser> findByUser_name(@Param("user_name") String user_name);
}
