package com.example.spring_boot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevProjectRepository extends JpaRepository<DevProject, Integer> {
}
