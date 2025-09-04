-- 航线文件管理系统数据库初始化脚本
-- 基于PostgreSQL 15+
-- 创建时间: 2024-01-01

-- 1. 创建航线表 (uav_routes)
CREATE TABLE IF NOT EXISTS uav_routes (
    id BIGSERIAL PRIMARY KEY,
    route_name VARCHAR(255) NOT NULL,
    template_type VARCHAR(100),
    drone_type VARCHAR(100),
    finish_action VARCHAR(100),
    exit_on_rc_lost_action VARCHAR(100),
    global_height DECIMAL(10,2),
    auto_flight_speed DECIMAL(8,2),
    waypoint_heading_param JSONB,
    waypoint_turn_param JSONB,
    gimbal_pitch_mode VARCHAR(50),
    payload_position VARCHAR(50),
    image_format VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    version INTEGER DEFAULT 0
);

-- 添加航线表字段注释
COMMENT ON COLUMN uav_routes.id IS '主键ID';
COMMENT ON COLUMN uav_routes.route_name IS '航线名称';
COMMENT ON COLUMN uav_routes.template_type IS '模板类型';
COMMENT ON COLUMN uav_routes.drone_type IS '无人机类型';
COMMENT ON COLUMN uav_routes.finish_action IS '完成动作';
COMMENT ON COLUMN uav_routes.exit_on_rc_lost_action IS 'RC丢失时退出动作';
COMMENT ON COLUMN uav_routes.global_height IS '全局高度';
COMMENT ON COLUMN uav_routes.auto_flight_speed IS '自动飞行速度';
COMMENT ON COLUMN uav_routes.waypoint_heading_param IS '航点朝向参数';
COMMENT ON COLUMN uav_routes.waypoint_turn_param IS '航点转弯参数';
COMMENT ON COLUMN uav_routes.gimbal_pitch_mode IS '云台俯仰模式';
COMMENT ON COLUMN uav_routes.payload_position IS '载荷位置';
COMMENT ON COLUMN uav_routes.image_format IS '图像格式';
COMMENT ON COLUMN uav_routes.created_at IS '创建时间';
COMMENT ON COLUMN uav_routes.updated_at IS '更新时间';
COMMENT ON COLUMN uav_routes.is_deleted IS '是否删除';
COMMENT ON COLUMN uav_routes.version IS '版本号';}]}}}

-- 2. 创建航点表 (uav_waypoints)
CREATE TABLE IF NOT EXISTS uav_waypoints (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL,
    waypoint_index INTEGER NOT NULL,
    longitude DECIMAL(12,8),
    latitude DECIMAL(12,8),
    height DECIMAL(10,2),
    speed DECIMAL(8,2),
    heading_param JSONB,
    turn_param JSONB,
    gimbal_param JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    version INTEGER DEFAULT 0
);

-- 添加航点表字段注释
COMMENT ON COLUMN uav_waypoints.id IS '主键ID';
COMMENT ON COLUMN uav_waypoints.route_id IS '航线ID';
COMMENT ON COLUMN uav_waypoints.waypoint_index IS '航点索引';
COMMENT ON COLUMN uav_waypoints.longitude IS '经度';
COMMENT ON COLUMN uav_waypoints.latitude IS '纬度';
COMMENT ON COLUMN uav_waypoints.height IS '高度';
COMMENT ON COLUMN uav_waypoints.speed IS '速度';
COMMENT ON COLUMN uav_waypoints.heading_param IS '朝向参数';
COMMENT ON COLUMN uav_waypoints.turn_param IS '转弯参数';
COMMENT ON COLUMN uav_waypoints.gimbal_param IS '云台参数';
COMMENT ON COLUMN uav_waypoints.created_at IS '创建时间';
COMMENT ON COLUMN uav_waypoints.updated_at IS '更新时间';
COMMENT ON COLUMN uav_waypoints.is_deleted IS '是否删除';
COMMENT ON COLUMN uav_waypoints.version IS '版本号';}]}}}

-- 3. 创建航点动作表 (uav_waypoint_actions)
CREATE TABLE IF NOT EXISTS uav_waypoint_actions (
    id BIGSERIAL PRIMARY KEY,
    waypoint_id BIGINT NOT NULL,
    action_type VARCHAR(100) NOT NULL,
    action_index INTEGER NOT NULL,
    action_param JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    version INTEGER DEFAULT 0
);

-- 添加航点动作表字段注释
COMMENT ON COLUMN uav_waypoint_actions.id IS '主键ID';
COMMENT ON COLUMN uav_waypoint_actions.waypoint_id IS '航点ID';
COMMENT ON COLUMN uav_waypoint_actions.action_type IS '动作类型';
COMMENT ON COLUMN uav_waypoint_actions.action_index IS '动作索引';
COMMENT ON COLUMN uav_waypoint_actions.action_param IS '动作参数';
COMMENT ON COLUMN uav_waypoint_actions.created_at IS '创建时间';
COMMENT ON COLUMN uav_waypoint_actions.updated_at IS '更新时间';
COMMENT ON COLUMN uav_waypoint_actions.is_deleted IS '是否删除';
COMMENT ON COLUMN uav_waypoint_actions.version IS '版本号';}]}}}

-- 4. 创建坐标点表 (coordinate_points)
CREATE TABLE IF NOT EXISTS coordinate_points (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL,
    point_type VARCHAR(50) NOT NULL,
    point_index INTEGER NOT NULL,
    longitude DECIMAL(12,8) NOT NULL,
    latitude DECIMAL(12,8) NOT NULL,
    height DECIMAL(10,2),
    point_param JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    version INTEGER DEFAULT 0
);

-- 添加坐标点表字段注释
COMMENT ON COLUMN coordinate_points.id IS '主键ID';
COMMENT ON COLUMN coordinate_points.route_id IS '航线ID';
COMMENT ON COLUMN coordinate_points.point_type IS '点类型';
COMMENT ON COLUMN coordinate_points.point_index IS '点索引';
COMMENT ON COLUMN coordinate_points.longitude IS '经度';
COMMENT ON COLUMN coordinate_points.latitude IS '纬度';
COMMENT ON COLUMN coordinate_points.height IS '高度';
COMMENT ON COLUMN coordinate_points.point_param IS '点参数';
COMMENT ON COLUMN coordinate_points.created_at IS '创建时间';
COMMENT ON COLUMN coordinate_points.updated_at IS '更新时间';
COMMENT ON COLUMN coordinate_points.is_deleted IS '是否删除';
COMMENT ON COLUMN coordinate_points.version IS '版本号';}]}}}

-- 5. 创建航线文件表 (route_files)
CREATE TABLE IF NOT EXISTS route_files (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(50) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_size BIGINT,
    file_hash VARCHAR(64),
    mime_type VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    version INTEGER DEFAULT 0
);

-- 添加航线文件表字段注释
COMMENT ON COLUMN route_files.id IS '主键ID';
COMMENT ON COLUMN route_files.route_id IS '航线ID';
COMMENT ON COLUMN route_files.file_name IS '文件名';
COMMENT ON COLUMN route_files.file_type IS '文件类型';
COMMENT ON COLUMN route_files.file_path IS '文件路径';
COMMENT ON COLUMN route_files.file_size IS '文件大小';
COMMENT ON COLUMN route_files.file_hash IS '文件哈希值';
COMMENT ON COLUMN route_files.mime_type IS 'MIME类型';
COMMENT ON COLUMN route_files.created_at IS '创建时间';
COMMENT ON COLUMN route_files.updated_at IS '更新时间';
COMMENT ON COLUMN route_files.is_deleted IS '是否删除';
COMMENT ON COLUMN route_files.version IS '版本号';}]}}}

-- 创建索引
-- 航线表索引
CREATE INDEX IF NOT EXISTS idx_uav_routes_name ON uav_routes(route_name);
CREATE INDEX IF NOT EXISTS idx_uav_routes_template_type ON uav_routes(template_type);
CREATE INDEX IF NOT EXISTS idx_uav_routes_drone_type ON uav_routes(drone_type);
CREATE INDEX IF NOT EXISTS idx_uav_routes_deleted ON uav_routes(is_deleted);
CREATE INDEX IF NOT EXISTS idx_uav_routes_created_at ON uav_routes(created_at);

-- 航点表索引
CREATE INDEX IF NOT EXISTS idx_uav_waypoints_route_id ON uav_waypoints(route_id);
CREATE INDEX IF NOT EXISTS idx_uav_waypoints_index ON uav_waypoints(waypoint_index);
CREATE INDEX IF NOT EXISTS idx_uav_waypoints_deleted ON uav_waypoints(is_deleted);
CREATE UNIQUE INDEX IF NOT EXISTS uk_uav_waypoints_route_index ON uav_waypoints(route_id, waypoint_index) WHERE is_deleted = FALSE;

-- 航点动作表索引
CREATE INDEX IF NOT EXISTS idx_uav_waypoint_actions_waypoint_id ON uav_waypoint_actions(waypoint_id);
CREATE INDEX IF NOT EXISTS idx_uav_waypoint_actions_type ON uav_waypoint_actions(action_type);
CREATE INDEX IF NOT EXISTS idx_uav_waypoint_actions_deleted ON uav_waypoint_actions(is_deleted);
CREATE UNIQUE INDEX IF NOT EXISTS uk_uav_waypoint_actions_waypoint_index ON uav_waypoint_actions(waypoint_id, action_index) WHERE is_deleted = FALSE;

-- 坐标点表索引
CREATE INDEX IF NOT EXISTS idx_coordinate_points_route_id ON coordinate_points(route_id);
CREATE INDEX IF NOT EXISTS idx_coordinate_points_type ON coordinate_points(point_type);
CREATE INDEX IF NOT EXISTS idx_coordinate_points_deleted ON coordinate_points(is_deleted);
CREATE UNIQUE INDEX IF NOT EXISTS uk_coordinate_points_route_type_index ON coordinate_points(route_id, point_type, point_index) WHERE is_deleted = FALSE;

-- 航线文件表索引
CREATE INDEX IF NOT EXISTS idx_route_files_route_id ON route_files(route_id);
CREATE INDEX IF NOT EXISTS idx_route_files_type ON route_files(file_type);
CREATE INDEX IF NOT EXISTS idx_route_files_hash ON route_files(file_hash);
CREATE INDEX IF NOT EXISTS idx_route_files_path ON route_files(file_path);
CREATE INDEX IF NOT EXISTS idx_route_files_deleted ON route_files(is_deleted);

-- 添加外键约束（可选，根据实际需求决定是否启用）
-- ALTER TABLE uav_waypoints ADD CONSTRAINT fk_waypoints_route_id FOREIGN KEY (route_id) REFERENCES uav_routes(id);
-- ALTER TABLE uav_waypoint_actions ADD CONSTRAINT fk_actions_waypoint_id FOREIGN KEY (waypoint_id) REFERENCES uav_waypoints(id);
-- ALTER TABLE coordinate_points ADD CONSTRAINT fk_coordinates_route_id FOREIGN KEY (route_id) REFERENCES uav_routes(id);
-- ALTER TABLE route_files ADD CONSTRAINT fk_files_route_id FOREIGN KEY (route_id) REFERENCES uav_routes(id);

-- 添加表注释
COMMENT ON TABLE uav_routes IS '无人机航线表';
COMMENT ON TABLE uav_waypoints IS '无人机航点表';
COMMENT ON TABLE uav_waypoint_actions IS '无人机航点动作表';
COMMENT ON TABLE coordinate_points IS '坐标点表';
COMMENT ON TABLE route_files IS '航线文件表';

-- 插入测试数据（可选）
-- INSERT INTO uav_routes (route_name, template_type, drone_type, finish_action, global_height, auto_flight_speed) 
-- VALUES ('测试航线1', 'waypoint', 'DJI_MINI_3', 'RTH', 100.00, 5.00);

COMMIT;