package com.thoughtworks.training.kmj.todoservice.repository;

import com.thoughtworks.training.kmj.todoservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findOneByName(String username);




    @Query("select u from User u where u.name = :username")
    User findIdByName(@Param("username") String username);


}
