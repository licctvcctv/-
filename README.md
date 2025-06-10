# 基于大数据的电气设备故障检测系统

## 项目简介
这是一个基于Spring Boot和大数据技术的电气设备故障检测系统，旨在通过数据分析和机器学习技术来预测和检测电气设备的故障。

## 项目结构
```
├── springbootcuoic46g/          # Spring Boot后端项目
│   ├── src/                     # 源代码目录
│   ├── db/                      # 数据库脚本
│   └── pom.xml                  # Maven配置文件
├── spider/                      # 数据爬虫模块
│   ├── Spider/                  # Scrapy爬虫项目
│   ├── requirements.txt         # Python依赖
│   └── run.py                   # 运行脚本
├── springboot开发文档.docx       # 开发文档
└── 基于大数据的电气设备故障检测_ic46g_数据库文档.doc  # 数据库文档
```

## 技术栈
- **后端**: Spring Boot, Java
- **数据库**: MySQL
- **数据采集**: Python, Scrapy
- **构建工具**: Maven

## 快速开始

### 环境要求
- Java 8+
- Maven 3.6+
- MySQL 5.7+
- Python 3.7+

### 安装步骤
1. 克隆项目
```bash
git clone https://github.com/licctvcctv/-.git
cd springboot_基于大数据的电气设备故障检测_cuoic46g
```

2. 数据库配置
- 导入数据库脚本：`springbootcuoic46g/db/springbootcuoic46g.sql`
- 修改数据库连接配置

3. 启动后端服务
```bash
cd springbootcuoic46g
mvn spring-boot:run
```

4. 安装Python依赖（数据爬虫）
```bash
cd spider
pip install -r requirements.txt
```

## 功能特性
- 电气设备数据采集
- 故障检测算法
- 数据可视化
- 预警系统
- 历史数据分析

## 贡献
欢迎提交Issue和Pull Request来改进这个项目。

## 许可证
本项目采用MIT许可证。
