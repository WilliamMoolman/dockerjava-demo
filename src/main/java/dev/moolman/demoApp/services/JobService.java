package dev.moolman.demoApp.services;

import dev.moolman.demoApp.entities.Job;
import dev.moolman.demoApp.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    JobRepo repo;

    public List<Job> getAll() {
        return this.repo.findAll();
    }

    public Job add(Job job) {
        return this.repo.save(job);
    }
}
