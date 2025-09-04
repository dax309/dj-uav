package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 无人机航点信息表
 * Author: Cleaner
 * Date: 2024/12/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("uav_waypoint")
public class UavWaypoint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 航线ID
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
    private Double longitude;

    /**
     * 纬度
     */
    @TableField("latitude")
    private Double latitude;

    /**
     * 高度
     */
    @TableField("height")
    private Double height;

    /**
     * 飞行速度
     */
    @TableField("speed")
    private Double speed;

    /**
     * 航点偏航角模式
     */
    @TableField("waypoint_heading_mode")
    private String waypointHeadingMode;

    /**
     * 航点偏航角度
     */
    @TableField("waypoint_heading_angle")
    private Double waypointHeadingAngle;

    /**
     * 航点兴趣点
     */
    @TableField("waypoint_poi_point")
    private String waypointPoiPoint;

    /**
     * 航点转弯模式
     */
    @TableField("waypoint_turn_mode")
    private String waypointTurnMode;

    /**
     * 航点转弯截距
     */
    @TableField("waypoint_turn_damping_dist")
    private Double waypointTurnDampingDist;

    /**
     * 该航段是否贴合直线
     */
    @TableField("use_straight_line")
    private Integer useStraightLine;

    /**
     * 航点云台俯仰角
     */
    @TableField("gimbal_pitch_angle")
    private Double gimbalPitchAngle;

    /**
     * 等时拍照间隔时间 单位s
     */
    @TableField("time_interval")
    private Double timeInterval;

    /**
     * 等距拍照间隔距离 单位m
     */
    @TableField("distance_interval")
    private Double distanceInterval;

    /**
     * 停止间隔拍照航点编号
     */
    @TableField("end_interval_route_index")
    private Integer endIntervalRouteIndex;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 逻辑删除标志：0-未删除，1-已删除
     */
    @TableLogic
    @TableField("deleted")
    private Integer deleted;
}
