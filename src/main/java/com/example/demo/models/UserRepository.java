package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByColor(String color);
    List<User> findByGpa(double gpa);
    List<User> findByHeight(int height);
    List<User> findByName(String name);
    List<User> findByUid(int uid);
    List<User> findByWeight(int weight);
}
