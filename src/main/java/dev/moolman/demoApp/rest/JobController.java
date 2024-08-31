package dev.moolman.demoApp.rest;

import dev.moolman.demoApp.entities.Job;
import dev.moolman.demoApp.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    JobService service;

    @GetMapping
    List<Job> getAll() {
        return this.service.getAll();
    }

    @PostMapping
    Job create(@RequestBody Job job) {
        return this.service.add(job);
    }
}
