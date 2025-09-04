package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 无人机航点动作表
 * Author: Cleaner
 * Date: 2024/12/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("uav_waypoint_action")
public class UavWaypointAction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 航点ID
     */
    @TableField("waypoint_id")
    private Long waypointId;

    /**
     * 航线ID（冗余字段，便于查询）
     */
    @TableField("route_id")
    private Long routeId;

    /**
     * 动作编号
     */
    @TableField("action_index")
    private Integer actionIndex;

    /**
     * 飞行器悬停等待时间
     */
    @TableField("hover_time")
    private Double hoverTime;

    /**
     * 飞行器目标偏航角
     */
    @TableField("aircraft_heading")
    private Double aircraftHeading;

    /**
     * 普通拍照：0，全景拍照：1
     */
    @TableField("take_photo_type")
    private Integer takePhotoType;

    /**
     * 是否使用全局拍照模式 0：不使用 1：使用
     */
    @TableField("use_global_image_format")
    private Integer useGlobalImageFormat;

    /**
     * 拍照模式（字典）
     */
    @TableField("image_format")
    private String imageFormat;

    /**
     * 云台偏航角
     */
    @TableField("gimbal_yaw_rotate_angle")
    private Double gimbalYawRotateAngle;

    /**
     * 云台俯仰角
     */
    @TableField("gimbal_pitch_rotate_angle")
    private Double gimbalPitchRotateAngle;

    /**
     * 变焦焦距
     */
    @TableField("zoom")
    private Double zoom;

    /**
     * 开始录像
     */
    @TableField("start_record")
    private Boolean startRecord;

    /**
     * 停止录像
     */
    @TableField("stop_record")
    private Boolean stopRecord;

    /**
     * 动作类型：0-航点动作，1-航线初始动作
     */
    @TableField("action_type")
    private Integer actionType;

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
