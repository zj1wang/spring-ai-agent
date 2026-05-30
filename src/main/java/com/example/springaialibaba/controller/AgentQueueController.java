package com.example.springaialibaba.controller;

import com.example.springaialibaba.service.AgentQueueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queue")
public class AgentQueueController {

    private final AgentQueueService agentQueueService;

    public AgentQueueController(AgentQueueService agentQueueService) {
        this.agentQueueService = agentQueueService;
    }

    @PostMapping("/submit")
    public String submitTask(@RequestBody String task) {
        return agentQueueService.submitTask(task);
    }

    @GetMapping("/results")
    public List<AgentQueueService.AgentTaskResult> getResults() {
        return agentQueueService.getResults();
    }

    @GetMapping("/size")
    public int getQueueSize() {
        return agentQueueService.getQueueSize();
    }

    @DeleteMapping("/clear")
    public String clearResults() {
        agentQueueService.clearResults();
        return "结果已清空";
    }
}
