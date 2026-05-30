# Spring AI Alibaba Agent Demo

基于 Spring Boot 3.2 和 Spring AI Alibaba 的智能 Agent 演示项目。

## ✨ 功能特性

### 1. Skills（可执行技能）
- **WeatherSkill** - 天气查询
- **CalculatorSkill** - 数学计算

### 2. MCP 接口（2个）
- **TranslationMcp** - 翻译服务（中译英/英译中）
- **SentimentAnalysisMcp** - 情感分析（分析/打分）

### 3. Spring AI Alibaba 特性
- **上下文压缩** - 自动压缩对话历史
- **Agent 队列** - 异步任务队列处理

## 📁 项目结构

```
src/main/java/com/example/springaialibaba/
├── SpringAiAlibabaAgentDemoApplication.java  # 启动类
├── skill/
│   ├── WeatherSkill.java                     # 天气技能
│   └── CalculatorSkill.java                  # 计算技能
├── mcp/
│   ├── TranslationMcp.java                   # 翻译接口
│   └── SentimentAnalysisMcp.java             # 情感分析接口
├── service/
│   ├── ContextCompressionService.java        # 上下文压缩服务
│   └── AgentQueueService.java                # Agent 队列服务
└── controller/
    ├── SkillController.java                  # 技能控制器
    ├── ContextCompressionController.java     # 上下文压缩控制器
    └── AgentQueueController.java             # 队列控制器
```

## 🚀 运行方式

```bash
cd spring-ai-agent
./gradlew bootRun
```

## 🔌 API 接口

### Skills
| 接口 | 方法 | 说明 |
|------|------|------|
| `/api/skills/weather?city=北京` | GET | 获取天气 |
| `/api/skills/calculator?expression=1+2*3` | GET | 数学计算 |

### MCP 接口
| 接口 | 方法 | 说明 |
|------|------|------|
| `/mcp/translation/zh-en` | POST | 中译英 |
| `/mcp/translation/en-zh` | POST | 英译中 |
| `/mcp/sentiment/analyze` | POST | 情感分析 |
| `/mcp/sentiment/score` | POST | 情感打分 |

### 上下文压缩
| 接口 | 方法 | 说明 |
|------|------|------|
| `/api/context/compress` | POST | 添加消息并压缩 |
| `/api/context/history` | GET | 获取历史 |
| `/api/context/clear` | DELETE | 清空上下文 |

### Agent 队列
| 接口 | 方法 | 说明 |
|------|------|------|
| `/api/queue/submit` | POST | 提交任务 |
| `/api/queue/results` | GET | 获取结果 |
| `/api/queue/size` | GET | 队列长度 |
| `/api/queue/clear` | DELETE | 清空结果 |

### 聊天
| 接口 | 方法 | 说明 |
|------|------|------|
| `/api/chat?message=Hello` | GET | 聊天 |
| `/api/chat` | POST | 聊天 |

## 📝 配置说明

在 `application.yml` 中配置 API Key：

```yaml
spring:
  ai:
    dashscope:
      api-key: your-api-key
```
