# 无人机航线管理系统数据库设计

## 项目概述

本项目是基于SpringBoot 3+、JDK 17+、MyBatis Plus和PostgreSQL技术栈开发的无人机航线管理系统。系统以`buildKmz`和`parseKmz`两个核心方法为基础，设计了完整的数据库表结构来支持航线数据的存储和管理。

## 技术栈

- **后端框架**: SpringBoot 3.3.5
- **Java版本**: JDK 17+
- **ORM框架**: MyBatis Plus 3.5.7
- **数据库**: PostgreSQL
- **构建工具**: Maven

## 数据库设计特点

### 1. 分层设计
- **航线基础信息层**: `uav_route` 表存储航线的全局配置和基本信息
- **航点详细信息层**: `uav_waypoint` 表存储各个航点的具体信息
- **动作指令层**: `uav_waypoint_action` 表存储航点的具体动作指令
- **特殊参数层**: `uav_route_mapping` 和 `uav_route_coordinate` 表存储建图参数和测区坐标

### 2. 查询优化
- **粗略查询**: 直接查询航线基础信息表，快速获取航线列表
- **详细查询**: 通过关联查询获取完整的航线、航点、动作信息
- **索引设计**: 为常用查询字段建立合适的索引，提升查询性能

### 3. 数据完整性
- **外键约束**: 保证数据关联的完整性
- **逻辑删除**: 使用deleted字段实现软删除
- **自动填充**: 自动填充创建时间、更新时间等字段

## 表结构说明

### 核心表

1. **uav_route** - 航线基础信息表
   - 存储航线的全局配置参数
   - 包含无人机类型、负载配置、飞行参数等
   - 支持航线状态管理

2. **uav_waypoint** - 航点信息表
   - 存储航线的各个航点详细信息
   - 包含GPS坐标、飞行参数、云台控制等
   - 支持航点级别的参数覆盖

3. **uav_waypoint_action** - 航点动作表
   - 存储航点的具体动作指令
   - 包含拍照、录像、云台控制等动作
   - 支持动作类型分类

4. **uav_route_mapping** - 建图参数表
   - 存储建图航拍、倾斜摄影等特殊航线的参数
   - 包含重叠率、采集方式、镜头类型等
   - 一对一关联航线表

5. **uav_route_coordinate** - 测区坐标表
   - 存储测区多边形的坐标点信息
   - 支持复杂测区形状定义
   - 一对多关联航线表

## 文件结构

```
src/main/java/com/cleaner/djuav/
├── entity/                    # 实体类
│   ├── UavRoute.java         # 航线基础信息实体
│   ├── UavWaypoint.java      # 航点信息实体
│   ├── UavWaypointAction.java # 航点动作实体
│   ├── UavRouteMapping.java  # 建图参数实体
│   └── UavRouteCoordinate.java # 测区坐标实体
├── mapper/                   # Mapper接口
│   ├── UavRouteMapper.java
│   ├── UavWaypointMapper.java
│   ├── UavWaypointActionMapper.java
│   ├── UavRouteMappingMapper.java
│   └── UavRouteCoordinateMapper.java
├── service/impl/             # 服务实现
│   └── UavRouteServiceImpl.java
└── config/                   # 配置类
    └── MyBatisPlusConfig.java

src/main/resources/
├── sql/
│   └── init_database.sql     # 数据库初始化脚本
└── application.yaml          # 应用配置文件
```

## 快速开始

### 1. 环境准备
- JDK 17+
- PostgreSQL 12+
- Maven 3.6+

### 2. 数据库初始化
```sql
-- 创建数据库
CREATE DATABASE djuav_route_db;

-- 执行初始化脚本
\i src/main/resources/sql/init_database.sql
```

### 3. 配置数据库连接
修改 `src/main/resources/application.yaml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/djuav_route_db
    username: your_username
    password: your_password
```

### 4. 运行项目
```bash
mvn clean compile
mvn spring-boot:run
```

## 核心功能

### 1. buildKmz 方法
- 接收航线配置参数
- 保存航线数据到数据库
- 生成KMZ文件

### 2. parseKmz 方法
- 解析KMZ文件
- 提取航线数据
- 返回结构化数据

### 3. 数据查询
- 航线列表查询（基本信息）
- 航线详情查询（包含航点、动作）
- 按状态查询航线
- 建图参数查询
- 测区坐标查询

## 性能优化

1. **索引优化**: 为常用查询字段建立合适的索引
2. **分页查询**: 使用MyBatis Plus分页插件
3. **缓存策略**: 可配置Redis缓存热点数据
4. **连接池**: 使用HikariCP连接池
5. **批量操作**: 支持批量插入和更新

## 扩展性

1. **字段扩展**: 预留扩展字段，支持新功能
2. **表结构扩展**: 支持新增业务表
3. **分区策略**: 大数据量时考虑表分区
4. **数据迁移**: 支持数据版本升级

## 安全考虑

1. **数据备份**: 定期备份重要数据
2. **访问控制**: 基于角色的数据访问控制
3. **数据加密**: 敏感数据加密存储
4. **审计日志**: 记录数据变更日志

## 开发规范

1. **命名规范**: 使用下划线命名法
2. **字段类型**: 使用合适的数据类型
3. **注释规范**: 为表和字段添加详细注释
4. **版本控制**: 使用数据库版本管理

## 联系方式

- 作者: Cleaner
- 邮箱: [您的邮箱]
- 项目地址: [项目地址]

## 许可证

本项目采用 [许可证名称] 许可证。
