package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 无人机航线基础信息表
 * Author: Cleaner
 * Date: 2024/12/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("uav_route")
public class UavRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 航线名称
     */
    @TableField("route_name")
    private String routeName;

    /**
     * 航线描述
     */
    @TableField("route_description")
    private String routeDescription;

    /**
     * 航线类型
     */
    @TableField("template_type")
    private String templateType;

    /**
     * 无人机类型
     */
    @TableField("drone_type")
    private Integer droneType;

    /**
     * 无人机子类型
     */
    @TableField("sub_drone_type")
    private Integer subDroneType;

    /**
     * 负载类型
     */
    @TableField("payload_type")
    private Integer payloadType;

    /**
     * 负载挂载位置
     */
    @TableField("payload_position")
    private Integer payloadPosition;

    /**
     * 负载图片存储类型
     */
    @TableField("image_format")
    private String imageFormat;

    /**
     * 航线结束动作
     */
    @TableField("finish_action")
    private String finishAction;

    /**
     * 失控动作
     */
    @TableField("exit_on_rc_lost_action")
    private String exitOnRcLostAction;

    /**
     * 全局航线高度
     */
    @TableField("global_height")
    private Double globalHeight;

    /**
     * 全局航线飞行速度
     */
    @TableField("auto_flight_speed")
    private Double autoFlightSpeed;

    /**
     * 全局偏航角模式
     */
    @TableField("waypoint_heading_mode")
    private String waypointHeadingMode;

    /**
     * 全局偏航角度
     */
    @TableField("waypoint_heading_angle")
    private Double waypointHeadingAngle;

    /**
     * 全局兴趣点
     */
    @TableField("waypoint_poi_point")
    private String waypointPoiPoint;

    /**
     * 全局航点转弯模式
     */
    @TableField("waypoint_turn_mode")
    private String waypointTurnMode;

    /**
     * 全局航点转弯截距
     */
    @TableField("waypoint_turn_damping_dist")
    private Double waypointTurnDampingDist;

    /**
     * 该航段是否贴合直线
     */
    @TableField("use_straight_line")
    private Integer useStraightLine;

    /**
     * 云台俯仰角控制模式
     */
    @TableField("gimbal_pitch_mode")
    private String gimbalPitchMode;

    /**
     * 参考起飞点
     */
    @TableField("take_off_ref_point")
    private String takeOffRefPoint;

    /**
     * 航线状态：0-草稿，1-已发布，2-已执行
     */
    @TableField("route_status")
    private Integer routeStatus;

    /**
     * KMZ文件路径
     */
    @TableField("kmz_file_path")
    private String kmzFilePath;

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
