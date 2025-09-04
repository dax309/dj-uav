-- 航线文件管理系统数据库初始化脚本
-- 基于实体类定义生成的正确建表语句
-- PostgreSQL 15+

-- 创建枚举类型
CREATE TYPE template_type_enum AS ENUM (
    'waypoint',      -- 航点飞行
    'mapping2d',     -- 建图航拍
    'mapping3d',     -- 倾斜摄影
    'strip'          -- 航带飞行
);

CREATE TYPE action_type_enum AS ENUM (
    'hover',         -- 悬停
    'take_photo',    -- 拍照
    'start_record',  -- 开始录像
    'stop_record',   -- 停止录像
    'gimbal_rotate', -- 云台旋转
    'aircraft_yaw'   -- 飞行器偏航
);

-- 1. 航线表 (uav_routes)
CREATE TABLE uav_routes (
    id BIGSERIAL PRIMARY KEY,
    route_name VARCHAR(255) NOT NULL,
    template_type template_type_enum NOT NULL DEFAULT 'waypoint',
    drone_type INTEGER,
    sub_drone_type INTEGER,
    payload_type INTEGER,
    
    -- 飞行参数
    auto_flight_speed DECIMAL(5,2),
    max_flight_speed DECIMAL(5,2),
    go_home_altitude DECIMAL(8,3),
    finish_action VARCHAR(50) DEFAULT 'goHome',
    exit_on_rc_lost VARCHAR(50) DEFAULT 'executeLostAction',
    execute_rc_lost_action VARCHAR(50) DEFAULT 'goHome',
    take_off_security_height DECIMAL(8,3),
    global_transition_speed DECIMAL(5,2),
    
    -- JSONB配置字段
    waypoint_heading_config JSONB,
    waypoint_turn_config JSONB,
    mapping_config JSONB,
    start_actions JSONB,
    
    -- 审计字段
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    version INTEGER DEFAULT 0
);

-- 2. 航点表 (uav_waypoints)
CREATE TABLE uav_waypoints (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL,
    route_point_index INTEGER NOT NULL,
    
    -- 位置信息
    longitude DECIMAL(12,9) NOT NULL,
    latitude DECIMAL(12,9) NOT NULL,
    height DECIMAL(10,3) NOT NULL,
    
    -- 飞行参数
    speed DECIMAL(5,2),
    gimbal_pitch_angle DECIMAL(6,2),
    
    -- 拍照参数
    time_interval DECIMAL(5,2),
    distance_interval DECIMAL(8,3),
    end_interval_route_index INTEGER,
    
    -- JSONB配置字段
    waypoint_heading_config JSONB,
    waypoint_turn_config JSONB,
    
    -- 审计字段
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    
    -- 外键约束
    CONSTRAINT fk_uav_waypoints_route_id FOREIGN KEY (route_id) REFERENCES uav_routes(id) ON DELETE CASCADE
);

-- 3. 航点动作表 (uav_waypoint_actions)
CREATE TABLE uav_waypoint_actions (
    id BIGSERIAL PRIMARY KEY,
    waypoint_id BIGINT NOT NULL,
    action_index INTEGER NOT NULL,
    action_type action_type_enum NOT NULL,
    
    -- 悬停参数
    hover_time DECIMAL(5,2),
    
    -- 飞行器参数
    aircraft_heading DECIMAL(6,2),
    
    -- 拍照参数
    take_photo_type INTEGER,
    use_global_image_format INTEGER,
    image_format VARCHAR(50),
    
    -- 云台参数
    gimbal_yaw_rotate_angle DECIMAL(6,2),
    gimbal_pitch_rotate_angle DECIMAL(6,2),
    
    -- 变焦参数
    zoom DECIMAL(5,2),
    
    -- 录像参数
    start_record BOOLEAN DEFAULT FALSE,
    stop_record BOOLEAN DEFAULT FALSE,
    
    -- 审计字段
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    
    -- 外键约束
    CONSTRAINT fk_uav_waypoint_actions_waypoint_id FOREIGN KEY (waypoint_id) REFERENCES uav_waypoints(id) ON DELETE CASCADE
);

-- 4. 坐标点表 (coordinate_points)
CREATE TABLE coordinate_points (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL,
    point_type VARCHAR(50) NOT NULL,
    point_index INTEGER NOT NULL,
    longitude DECIMAL(12,9) NOT NULL,
    latitude DECIMAL(12,9) NOT NULL,
    height DECIMAL(10,3),
    
    -- 审计字段
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    
    -- 外键约束
    CONSTRAINT fk_coordinate_points_route_id FOREIGN KEY (route_id) REFERENCES uav_routes(id) ON DELETE CASCADE
);

-- 5. 航线文件表 (route_files)
CREATE TABLE route_files (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_size BIGINT,
    file_type VARCHAR(50) DEFAULT 'KMZ',
    file_hash VARCHAR(64),
    mime_type VARCHAR(100),
    
    -- 审计字段
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    
    -- 外键约束
    CONSTRAINT fk_route_files_route_id FOREIGN KEY (route_id) REFERENCES uav_routes(id) ON DELETE CASCADE
);

-- 创建索引

-- 航线表索引
CREATE INDEX idx_uav_routes_template_type ON uav_routes(template_type);
CREATE INDEX idx_uav_routes_drone_type ON uav_routes(drone_type);
CREATE INDEX idx_uav_routes_created_at ON uav_routes(created_at DESC);
CREATE INDEX idx_uav_routes_is_deleted ON uav_routes(is_deleted);
CREATE INDEX idx_uav_routes_created_by ON uav_routes(created_by);

-- 航点表索引
CREATE UNIQUE INDEX idx_uav_waypoints_route_point ON uav_waypoints(route_id, route_point_index) WHERE is_deleted = FALSE;
CREATE INDEX idx_uav_waypoints_route_id ON uav_waypoints(route_id);
CREATE INDEX idx_uav_waypoints_coordinates ON uav_waypoints(longitude, latitude);
CREATE INDEX idx_uav_waypoints_is_deleted ON uav_waypoints(is_deleted);

-- 航点动作表索引
CREATE UNIQUE INDEX idx_uav_waypoint_actions_waypoint_action ON uav_waypoint_actions(waypoint_id, action_index) WHERE is_deleted = FALSE;
CREATE INDEX idx_uav_waypoint_actions_waypoint_id ON uav_waypoint_actions(waypoint_id);
CREATE INDEX idx_uav_waypoint_actions_action_type ON uav_waypoint_actions(action_type);
CREATE INDEX idx_uav_waypoint_actions_is_deleted ON uav_waypoint_actions(is_deleted);

-- 坐标点表索引
CREATE INDEX idx_coordinate_points_route_id ON coordinate_points(route_id);
CREATE INDEX idx_coordinate_points_type ON coordinate_points(point_type);
CREATE INDEX idx_coordinate_points_coordinates ON coordinate_points(longitude, latitude);
CREATE INDEX idx_coordinate_points_is_deleted ON coordinate_points(is_deleted);

-- 航线文件表索引
CREATE INDEX idx_route_files_route_id ON route_files(route_id);
CREATE INDEX idx_route_files_file_type ON route_files(file_type);
CREATE INDEX idx_route_files_created_at ON route_files(created_at DESC);
CREATE INDEX idx_route_files_is_deleted ON route_files(is_deleted);

-- 添加检查约束

-- 航点动作表检查约束
ALTER TABLE uav_waypoint_actions ADD CONSTRAINT chk_action_index CHECK (action_index >= 0);
ALTER TABLE uav_waypoint_actions ADD CONSTRAINT chk_hover_time CHECK (hover_time IS NULL OR hover_time >= 0);
ALTER TABLE uav_waypoint_actions ADD CONSTRAINT chk_aircraft_heading CHECK (aircraft_heading IS NULL OR (aircraft_heading >= -180 AND aircraft_heading <= 180));
ALTER TABLE uav_waypoint_actions ADD CONSTRAINT chk_gimbal_yaw_rotate_angle CHECK (gimbal_yaw_rotate_angle IS NULL OR (gimbal_yaw_rotate_angle >= -180 AND gimbal_yaw_rotate_angle <= 180));
ALTER TABLE uav_waypoint_actions ADD CONSTRAINT chk_gimbal_pitch_rotate_angle CHECK (gimbal_pitch_rotate_angle IS NULL OR (gimbal_pitch_rotate_angle >= -90 AND gimbal_pitch_rotate_angle <= 90));
ALTER TABLE uav_waypoint_actions ADD CONSTRAINT chk_zoom CHECK (zoom IS NULL OR zoom > 0);

-- 坐标点表检查约束
ALTER TABLE coordinate_points ADD CONSTRAINT chk_point_index CHECK (point_index >= 0);
ALTER TABLE coordinate_points ADD CONSTRAINT chk_longitude CHECK (longitude >= -180 AND longitude <= 180);
ALTER TABLE coordinate_points ADD CONSTRAINT chk_latitude CHECK (latitude >= -90 AND latitude <= 90);

-- 航点表检查约束
ALTER TABLE uav_waypoints ADD CONSTRAINT chk_route_point_index CHECK (route_point_index >= 0);
ALTER TABLE uav_waypoints ADD CONSTRAINT chk_waypoint_longitude CHECK (longitude >= -180 AND longitude <= 180);
ALTER TABLE uav_waypoints ADD CONSTRAINT chk_waypoint_latitude CHECK (latitude >= -90 AND latitude <= 90);
ALTER TABLE uav_waypoints ADD CONSTRAINT chk_speed CHECK (speed IS NULL OR speed > 0);
ALTER TABLE uav_waypoints ADD CONSTRAINT chk_gimbal_pitch_angle CHECK (gimbal_pitch_angle IS NULL OR (gimbal_pitch_angle >= -90 AND gimbal_pitch_angle <= 90));

-- 创建更新时间触发器函数
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- 为所有表添加更新时间触发器
CREATE TRIGGER update_uav_routes_updated_at BEFORE UPDATE ON uav_routes FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_uav_waypoints_updated_at BEFORE UPDATE ON uav_waypoints FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_uav_waypoint_actions_updated_at BEFORE UPDATE ON uav_waypoint_actions FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_coordinate_points_updated_at BEFORE UPDATE ON coordinate_points FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_route_files_updated_at BEFORE UPDATE ON route_files FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- 添加表注释
COMMENT ON TABLE uav_routes IS '无人机航线表';
COMMENT ON TABLE uav_waypoints IS '无人机航点表';
COMMENT ON TABLE uav_waypoint_actions IS '无人机航点动作表';
COMMENT ON TABLE coordinate_points IS '坐标点表';
COMMENT ON TABLE route_files IS '航线文件表';

-- 脚本执行完成
SELECT 'Database initialization completed successfully!' as status;