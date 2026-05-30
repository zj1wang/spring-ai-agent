package com.example.springaialibaba.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class AgentQueueService {

    private final ChatModel chatModel;
    private final Queue<AgentTask> taskQueue = new LinkedList<>();
    private final List<AgentTaskResult> results = Collections.synchronizedList(new ArrayList<>());
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private volatile boolean running = false;

    public AgentQueueService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String submitTask(String taskDescription) {
        String taskId = UUID.randomUUID().toString();
        AgentTask task = new AgentTask(taskId, taskDescription, System.currentTimeMillis());
        taskQueue.add(task);
        
        if (!running) {
            running = true;
            executorService.submit(this::processQueue);
        }
        
        return "任务已提交，任务ID: " + taskId + "，队列长度: " + taskQueue.size();
    }

    private void processQueue() {
        while (running) {
            AgentTask task = taskQueue.poll();
            if (task != null) {
                try {
                    String template = "请处理以下任务：\n{task}\n请用中文给出详细的解决方案。";
                    PromptTemplate promptTemplate = new PromptTemplate(template);
                    Prompt prompt = promptTemplate.create(Map.of("task", task.getDescription()));
                    String result = chatModel.call(prompt).getResult().getOutput().getContent();
                    
                    results.add(new AgentTaskResult(
                            task.getTaskId(),
                            task.getDescription(),
                            result,
                            System.currentTimeMillis(),
                            "SUCCESS"
                    ));
                } catch (Exception e) {
                    results.add(new AgentTaskResult(
                            task.getTaskId(),
                            task.getDescription(),
                            "执行失败: " + e.getMessage(),
                            System.currentTimeMillis(),
                            "FAILED"
                    ));
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public List<AgentTaskResult> getResults() {
        return new ArrayList<>(results);
    }

    public int getQueueSize() {
        return taskQueue.size();
    }

    public void clearResults() {
        results.clear();
    }

    public static class AgentTask {
        private final String taskId;
        private final String description;
        private final long submitTime;

        public AgentTask(String taskId, String description, long submitTime) {
            this.taskId = taskId;
            this.description = description;
            this.submitTime = submitTime;
        }

        public String getTaskId() { return taskId; }
        public String getDescription() { return description; }
        public long getSubmitTime() { return submitTime; }
    }

    public static class AgentTaskResult {
        private final String taskId;
        private final String description;
        private final String result;
        private final long completeTime;
        private final String status;

        public AgentTaskResult(String taskId, String description, String result, long completeTime, String status) {
            this.taskId = taskId;
            this.description = description;
            this.result = result;
            this.completeTime = completeTime;
            this.status = status;
        }

        public String getTaskId() { return taskId; }
        public String getDescription() { return description; }
        public String getResult() { return result; }
        public long getCompleteTime() { return completeTime; }
        public String getStatus() { return status; }
    }
}
