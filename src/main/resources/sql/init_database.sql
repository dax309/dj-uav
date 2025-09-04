-- 无人机航线管理系统数据库初始化脚本
-- PostgreSQL版本
-- Author: Cleaner
-- Date: 2024/12/22

-- 创建数据库（如果需要）
-- CREATE DATABASE djuav_route_db;

-- 使用数据库
-- \c djuav_route_db;

-- 1. 无人机航线基础信息表
CREATE TABLE IF NOT EXISTS uav_route (
    id BIGSERIAL PRIMARY KEY,
    route_name VARCHAR(255) NOT NULL COMMENT '航线名称',
    route_description TEXT COMMENT '航线描述',
    template_type VARCHAR(50) COMMENT '航线类型',
    drone_type INTEGER COMMENT '无人机类型',
    sub_drone_type INTEGER COMMENT '无人机子类型',
    payload_type INTEGER COMMENT '负载类型',
    payload_position INTEGER COMMENT '负载挂载位置',
    image_format VARCHAR(50) COMMENT '负载图片存储类型',
    finish_action VARCHAR(50) COMMENT '航线结束动作',
    exit_on_rc_lost_action VARCHAR(50) COMMENT '失控动作',
    global_height DECIMAL(10,3) COMMENT '全局航线高度',
    auto_flight_speed DECIMAL(10,3) COMMENT '全局航线飞行速度',
    waypoint_heading_mode VARCHAR(50) COMMENT '全局偏航角模式',
    waypoint_heading_angle DECIMAL(10,3) COMMENT '全局偏航角度',
    waypoint_poi_point VARCHAR(255) COMMENT '全局兴趣点',
    waypoint_turn_mode VARCHAR(50) COMMENT '全局航点转弯模式',
    waypoint_turn_damping_dist DECIMAL(10,3) COMMENT '全局航点转弯截距',
    use_straight_line INTEGER DEFAULT 0 COMMENT '该航段是否贴合直线',
    gimbal_pitch_mode VARCHAR(50) COMMENT '云台俯仰角控制模式',
    take_off_ref_point VARCHAR(255) COMMENT '参考起飞点',
    route_status INTEGER DEFAULT 0 COMMENT '航线状态：0-草稿，1-已发布，2-已执行',
    kmz_file_path VARCHAR(500) COMMENT 'KMZ文件路径',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(100) COMMENT '创建人',
    update_by VARCHAR(100) COMMENT '更新人',
    deleted INTEGER DEFAULT 0 COMMENT '逻辑删除标志：0-未删除，1-已删除'
);

-- 2. 无人机航点信息表
CREATE TABLE IF NOT EXISTS uav_waypoint (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL COMMENT '航线ID',
    route_point_index INTEGER NOT NULL COMMENT '航点编号',
    longitude DECIMAL(15,10) NOT NULL COMMENT '经度',
    latitude DECIMAL(15,10) NOT NULL COMMENT '纬度',
    height DECIMAL(10,3) COMMENT '高度',
    speed DECIMAL(10,3) COMMENT '飞行速度',
    waypoint_heading_mode VARCHAR(50) COMMENT '航点偏航角模式',
    waypoint_heading_angle DECIMAL(10,3) COMMENT '航点偏航角度',
    waypoint_poi_point VARCHAR(255) COMMENT '航点兴趣点',
    waypoint_turn_mode VARCHAR(50) COMMENT '航点转弯模式',
    waypoint_turn_damping_dist DECIMAL(10,3) COMMENT '航点转弯截距',
    use_straight_line INTEGER DEFAULT 0 COMMENT '该航段是否贴合直线',
    gimbal_pitch_angle DECIMAL(10,3) COMMENT '航点云台俯仰角',
    time_interval DECIMAL(10,3) COMMENT '等时拍照间隔时间 单位s',
    distance_interval DECIMAL(10,3) COMMENT '等距拍照间隔距离 单位m',
    end_interval_route_index INTEGER COMMENT '停止间隔拍照航点编号',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(100) COMMENT '创建人',
    update_by VARCHAR(100) COMMENT '更新人',
    deleted INTEGER DEFAULT 0 COMMENT '逻辑删除标志：0-未删除，1-已删除',
    FOREIGN KEY (route_id) REFERENCES uav_route(id) ON DELETE CASCADE
);

-- 3. 无人机航点动作表
CREATE TABLE IF NOT EXISTS uav_waypoint_action (
    id BIGSERIAL PRIMARY KEY,
    waypoint_id BIGINT COMMENT '航点ID',
    route_id BIGINT NOT NULL COMMENT '航线ID（冗余字段，便于查询）',
    action_index INTEGER NOT NULL COMMENT '动作编号',
    hover_time DECIMAL(10,3) COMMENT '飞行器悬停等待时间',
    aircraft_heading DECIMAL(10,3) COMMENT '飞行器目标偏航角',
    take_photo_type INTEGER COMMENT '普通拍照：0，全景拍照：1',
    use_global_image_format INTEGER COMMENT '是否使用全局拍照模式 0：不使用 1：使用',
    image_format VARCHAR(50) COMMENT '拍照模式（字典）',
    gimbal_yaw_rotate_angle DECIMAL(10,3) COMMENT '云台偏航角',
    gimbal_pitch_rotate_angle DECIMAL(10,3) COMMENT '云台俯仰角',
    zoom DECIMAL(10,3) COMMENT '变焦焦距',
    start_record BOOLEAN DEFAULT FALSE COMMENT '开始录像',
    stop_record BOOLEAN DEFAULT FALSE COMMENT '停止录像',
    action_type INTEGER DEFAULT 0 COMMENT '动作类型：0-航点动作，1-航线初始动作',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(100) COMMENT '创建人',
    update_by VARCHAR(100) COMMENT '更新人',
    deleted INTEGER DEFAULT 0 COMMENT '逻辑删除标志：0-未删除，1-已删除',
    FOREIGN KEY (waypoint_id) REFERENCES uav_waypoint(id) ON DELETE CASCADE,
    FOREIGN KEY (route_id) REFERENCES uav_route(id) ON DELETE CASCADE
);

-- 4. 无人机航线建图参数表
CREATE TABLE IF NOT EXISTS uav_route_mapping (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL COMMENT '航线ID',
    collection_method VARCHAR(50) COMMENT '采集方式',
    lens_type VARCHAR(50) COMMENT '镜头类型',
    overlap_h INTEGER COMMENT '航向重叠率',
    overlap_w INTEGER COMMENT '旁向重叠率',
    elevation_optimize_enable INTEGER COMMENT '是否开启高程优化',
    shoot_type VARCHAR(50) COMMENT '拍照模式',
    direction VARCHAR(50) COMMENT '航线方向',
    margin VARCHAR(100) COMMENT '测区外扩距离',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(100) COMMENT '创建人',
    update_by VARCHAR(100) COMMENT '更新人',
    deleted INTEGER DEFAULT 0 COMMENT '逻辑删除标志：0-未删除，1-已删除',
    FOREIGN KEY (route_id) REFERENCES uav_route(id) ON DELETE CASCADE
);

-- 5. 无人机航线测区坐标表
CREATE TABLE IF NOT EXISTS uav_route_coordinate (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL COMMENT '航线ID',
    coordinate_index INTEGER NOT NULL COMMENT '坐标点序号',
    longitude DECIMAL(15,10) NOT NULL COMMENT '经度',
    latitude DECIMAL(15,10) NOT NULL COMMENT '纬度',
    height DECIMAL(10,3) COMMENT '高度',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(100) COMMENT '创建人',
    update_by VARCHAR(100) COMMENT '更新人',
    deleted INTEGER DEFAULT 0 COMMENT '逻辑删除标志：0-未删除，1-已删除',
    FOREIGN KEY (route_id) REFERENCES uav_route(id) ON DELETE CASCADE
);

-- 创建索引
-- 航线表索引
CREATE INDEX IF NOT EXISTS idx_uav_route_status ON uav_route(route_status);
CREATE INDEX IF NOT EXISTS idx_uav_route_create_time ON uav_route(create_time);
CREATE INDEX IF NOT EXISTS idx_uav_route_template_type ON uav_route(template_type);

-- 航点表索引
CREATE INDEX IF NOT EXISTS idx_uav_waypoint_route_id ON uav_waypoint(route_id);
CREATE INDEX IF NOT EXISTS idx_uav_waypoint_index ON uav_waypoint(route_id, route_point_index);

-- 航点动作表索引
CREATE INDEX IF NOT EXISTS idx_uav_waypoint_action_waypoint_id ON uav_waypoint_action(waypoint_id);
CREATE INDEX IF NOT EXISTS idx_uav_waypoint_action_route_id ON uav_waypoint_action(route_id);
CREATE INDEX IF NOT EXISTS idx_uav_waypoint_action_type ON uav_waypoint_action(action_type);

-- 建图参数表索引
CREATE INDEX IF NOT EXISTS idx_uav_route_mapping_route_id ON uav_route_mapping(route_id);

-- 测区坐标表索引
CREATE INDEX IF NOT EXISTS idx_uav_route_coordinate_route_id ON uav_route_coordinate(route_id);
CREATE INDEX IF NOT EXISTS idx_uav_route_coordinate_index ON uav_route_coordinate(route_id, coordinate_index);

-- 创建更新时间触发器函数
CREATE OR REPLACE FUNCTION update_updated_time_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.update_time = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- 为所有表创建更新时间触发器
CREATE TRIGGER update_uav_route_updated_time BEFORE UPDATE ON uav_route FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();
CREATE TRIGGER update_uav_waypoint_updated_time BEFORE UPDATE ON uav_waypoint FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();
CREATE TRIGGER update_uav_waypoint_action_updated_time BEFORE UPDATE ON uav_waypoint_action FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();
CREATE TRIGGER update_uav_route_mapping_updated_time BEFORE UPDATE ON uav_route_mapping FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();
CREATE TRIGGER update_uav_route_coordinate_updated_time BEFORE UPDATE ON uav_route_coordinate FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();

-- 插入示例数据
INSERT INTO uav_route (
    route_name, route_description, template_type, drone_type, sub_drone_type,
    payload_type, payload_position, image_format, finish_action, exit_on_rc_lost_action,
    global_height, auto_flight_speed, waypoint_heading_mode, waypoint_heading_angle,
    waypoint_turn_mode, waypoint_turn_damping_dist, use_straight_line, gimbal_pitch_mode,
    take_off_ref_point, route_status, create_by
) VALUES (
    '示例航线', '这是一个示例航线', 'MAPPING', 1, 1,
    1, 1, 'JPEG', 'GO_HOME', 'HOVER',
    100.0, 5.0, 'FREE', 0.0,
    'COORDINATE_TURN', 0.0, 0, 'FREE',
    '0,0,0', 0, 'system'
);

-- 获取刚插入的航线ID
-- 注意：在实际应用中，应该使用应用程序来获取ID
-- 这里仅作为示例
