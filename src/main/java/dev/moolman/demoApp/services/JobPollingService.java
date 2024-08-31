package dev.moolman.demoApp.services;

import dev.moolman.demoApp.entities.Job;
import dev.moolman.demoApp.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPollingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JobRepo repo;

    @Autowired
    private DockerService dockerService;

    @Scheduled(fixedRate = 5000) // Poll every 5 seconds
    public void checkForNewJobs() {
        List<Job> pendingJobs = repo.findAllByStatus("PENDING");

        for (Job job : pendingJobs) {
            dockerService.runContainer(job.getAction(), "job-"+job.getId()+"-"+job.getAction());

            job.setStatus("RUNNING");
            repo.save(job);
        }
    }
}