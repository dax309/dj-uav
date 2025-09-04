package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 航点表实体类
 * 
 * @author SOLO Coding
 * @since 2024-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("uav_waypoints")
public class UavWaypoint {

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 外键，关联航线表
     */
    @TableField("route_id")
    private Long routeId;

    /**
     * 航点编号
     */
    @TableField("route_point_index")
    private Integer routePointIndex;

    /**
     * 经度
     */
    @TableField("longitude")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @TableField("latitude")
    private BigDecimal latitude;

    /**
     * 高度(米)
     */
    @TableField("height")
    private BigDecimal height;

    /**
     * 飞行速度(米/秒)
     */
    @TableField("speed")
    private BigDecimal speed;

    /**
     * 航点云台俯仰角
     */
    @TableField("gimbal_pitch_angle")
    private BigDecimal gimbalPitchAngle;

    /**
     * 等时拍照间隔时间(秒)
     */
    @TableField("time_interval")
    private BigDecimal timeInterval;

    /**
     * 等距拍照间隔距离(米)
     */
    @TableField("distance_interval")
    private BigDecimal distanceInterval;

    /**
     * 停止间隔拍照航点编号
     */
    @TableField("end_interval_route_index")
    private Integer endIntervalRouteIndex;

    /**
     * 航点偏航角配置
     */
    @TableField(value = "waypoint_heading_config", typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private JsonNode waypointHeadingConfig;

    /**
     * 航点转弯模式配置
     */
    @TableField(value = "waypoint_turn_config", typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private JsonNode waypointTurnConfig;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    /**
     * 软删除标记
     */
    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;

    /**
     * 设置航点索引
     */
    public void setWaypointIndex(int waypointIndex) {
        this.routePointIndex = waypointIndex;
    }
}