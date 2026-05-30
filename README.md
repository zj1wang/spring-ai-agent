# Spring AI Alibaba Agent Demo

基于 Spring Boot 3.2 和 Spring AI Alibaba 的后端项目 Demo

## ✅ 验证通过

项目已成功验证并启动！服务运行在 http://localhost:8080/

## 📦 当前项目结构

这是一个可正常运行的基础版本。包含：
- Spring Boot 3.2 Web 框架
- Gradle 构建系统
- REST API 接口

## 🚀 快速开始

### 运行项目

```bash
# Windows (确保已安装 JDK 17+)
$env:JAVA_HOME="C:\Program Files\Java\jdk-21.0.11"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
.\gradlew.bat bootRun
```

### 访问接口

| 接口 | 方法 | 说明 |
|------|------|------|
| `http://localhost:8080/` | GET | 首页 - 验证服务运行 |
| `http://localhost:8080/api/hello?name=Spring` | GET | 示例接口 |

```bash
# 测试接口
curl http://localhost:8080/
curl http://localhost:8080/api/hello
```

## 📝 添加 Spring AI Alibaba 功能

如需添加完整的 AI 功能（Skills、MCP 工具、Agent），请参考以下步骤：

### 1. 更新 build.gradle 依赖

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.example'
version = '1.0.0'

java {
    sourceCompatibility = '17'
}

repositories {
    mavenCentral()
    maven { url 'https://maven.aliyun.com/repository/public' }
    maven { url 'https://repo.spring.io/milestone' }
}

dependencyManagement {
    imports {
        mavenBom 'com.alibaba.cloud:spring-cloud-alibaba-dependencies:2023.0.1.0'
    }
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'com.alibaba.cloud:spring-cloud-starter-alibaba-ai'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

### 2. 配置 application.yml

```yaml
server:
  port: 8080

spring:
  application:
    name: spring-ai-alibaba-agent-demo
  ai:
    dashscope:
      api-key: sk-your-api-key-here
      chat:
        options:
          model: qwen-turbo
```

### 3. 添加 AI 聊天功能

```java
package com.example.springaialibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.model.ChatModel;

@SpringBootApplication
@RestController
public class SpringAiAlibabaAgentDemoApplication {

    private final ChatModel chatModel;

    public SpringAiAlibabaAgentDemoApplication(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAiAlibabaAgentDemoApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Spring AI Alibaba Agent Demo is running!";
    }

    @GetMapping("/api/chat")
    public String chat(@RequestParam String message) {
        return chatModel.call(message);
    }
}
```

## 🎯 完整功能列表 (规划)

- [x] Spring Boot 3.2 基础框架
- [ ] Skills（可执行技能）
  - [ ] 天气查询
  - [ ] 数学计算
  - [ ] 新闻摘要
- [ ] MCP 工具
  - [ ] 翻译工具
  - [ ] 情感分析
  - [ ] 文本生成
- [ ] Spring AI Alibaba 高级特性
  - [ ] ReactAgent
  - [ ] SummarizationHook（上下文压缩）
  - [ ] Agent Queue（任务队列）

## 📚 环境要求

- JDK 17+
- Gradle 8.5+
- 阿里云 DashScope API Key

## 📄 许可证

MIT License
