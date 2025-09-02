package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 航点表实体类
 * 对应数据库表：fcp_route_points
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "fcp_route_points")
public class RoutePoint {

    /**
     * 主键ID（雪花算法生成）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 航线ID
     */
    @TableField("route_id")
    private Long routeId;

    /**
     * 航点序号
     */
    @TableField("point_index")
    private Integer pointIndex;

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
     * 航点高度（米）
     */
    @TableField("height")
    private BigDecimal height;

    /**
     * 航点飞行速度（米/秒）
     */
    @TableField("speed")
    private BigDecimal speed;

    /**
     * 偏航角（度）
     */
    @TableField("yaw_angle")
    private BigDecimal yawAngle;

    /**
     * 转弯模式
     */
    @TableField("turn_mode")
    private String turnMode;

    /**
     * 云台俯仰角（度）
     */
    @TableField("gimbal_pitch_angle")
    private BigDecimal gimbalPitchAngle;

    /**
     * 航点偏航角模式
     */
    @TableField("waypoint_heading_mode")
    private String waypointHeadingMode;

    /**
     * 航点偏航角度
     */
    @TableField("waypoint_heading_angle")
    private BigDecimal waypointHeadingAngle;

    /**
     * 航点兴趣点坐标
     */
    @TableField("waypoint_poi_point")
    private String waypointPoiPoint;

    /**
     * 航点转弯模式
     */
    @TableField("waypoint_turn_mode")
    private String waypointTurnMode;

    /**
     * 航点转弯阻尼距离
     */
    @TableField("waypoint_turn_damping_dist")
    private BigDecimal waypointTurnDampingDist;

    /**
     * 是否使用直线飞行
     */
    @TableField("use_straight_line")
    private Integer useStraightLine;

    /**
     * 是否为起始或结束点
     */
    @TableField("is_start_and_end_point")
    private Boolean isStartAndEndPoint;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;
}