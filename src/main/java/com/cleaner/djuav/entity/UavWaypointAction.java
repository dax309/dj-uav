package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 航点动作表实体类
 * 
 * @author SOLO Coding
 * @since 2024-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("uav_waypoint_actions")
public class UavWaypointAction {

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 外键，关联航点表
     */
    @TableField("waypoint_id")
    private Long waypointId;

    /**
     * 动作编号(在航点内的顺序)
     */
    @TableField("action_index")
    private Integer actionIndex;

    /**
     * 动作类型
     */
    @TableField("action_type")
    private String actionType;

    /**
     * 悬停等待时间(秒)
     */
    @TableField("hover_time")
    private BigDecimal hoverTime;

    /**
     * 飞行器目标偏航角(度)
     */
    @TableField("aircraft_heading")
    private BigDecimal aircraftHeading;

    /**
     * 拍照类型(0:单拍 1:连拍)
     */
    @TableField("take_photo_type")
    private Integer takePhotoType;

    /**
     * 是否使用全局拍照模式(0:否 1:是)
     */
    @TableField("use_global_image_format")
    private Integer useGlobalImageFormat;

    /**
     * 拍照模式(wide:广角 zoom:变焦 ir:红外)
     */
    @TableField("image_format")
    private String imageFormat;

    /**
     * 云台偏航角(度)
     */
    @TableField("gimbal_yaw_rotate_angle")
    private BigDecimal gimbalYawRotateAngle;

    /**
     * 云台俯仰角(度)
     */
    @TableField("gimbal_pitch_rotate_angle")
    private BigDecimal gimbalPitchRotateAngle;

    /**
     * 变焦焦距
     */
    @TableField("zoom")
    private BigDecimal zoom;

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
     * 版本号，用于乐观锁
     */
    @Version
    @TableField("version")
    private Integer version;
}