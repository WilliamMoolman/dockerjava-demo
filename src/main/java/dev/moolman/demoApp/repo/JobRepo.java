package dev.moolman.demoApp.repo;

import dev.moolman.demoApp.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepo extends JpaRepository<Job, Integer> {
    List<Job> findAllByStatus(String status);
}
