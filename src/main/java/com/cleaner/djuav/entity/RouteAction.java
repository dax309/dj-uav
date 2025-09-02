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
 * 航线动作表实体类
 * 对应数据库表：fcp_route_actions
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "fcp_route_actions")
public class RouteAction {

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
     * 动作类型：start_action/global_action
     */
    @TableField("action_type")
    private String actionType;

    /**
     * 动作序号
     */
    @TableField("action_index")
    private Integer actionIndex;

    /**
     * 悬停时间（秒）
     */
    @TableField("hover_time")
    private BigDecimal hoverTime;

    /**
     * 飞行器偏航角（度）
     */
    @TableField("aircraft_yaw")
    private BigDecimal aircraftYaw;

    /**
     * 拍照类型
     */
    @TableField("shoot_photo_mode")
    private String shootPhotoMode;

    /**
     * 拍照距离间隔
     */
    @TableField("shoot_photo_distance_interval")
    private BigDecimal shootPhotoDistanceInterval;

    /**
     * 拍照时间间隔
     */
    @TableField("shoot_photo_time_interval")
    private BigDecimal shootPhotoTimeInterval;

    /**
     * 云台旋转模式
     */
    @TableField("gimbal_rotate_mode")
    private String gimbalRotateMode;

    /**
     * 云台俯仰旋转角度
     */
    @TableField("gimbal_pitch_rotate")
    private BigDecimal gimbalPitchRotate;

    /**
     * 云台偏航旋转角度
     */
    @TableField("gimbal_yaw_rotate")
    private BigDecimal gimbalYawRotate;

    /**
     * 云台横滚旋转角度
     */
    @TableField("gimbal_roll_rotate")
    private BigDecimal gimbalRollRotate;

    /**
     * 云台旋转时间参数
     */
    @TableField("gimbal_rotate_time_param")
    private BigDecimal gimbalRotateTimeParam;

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
     * 对焦参数
     */
    @TableField("focus_param")
    private BigDecimal focusParam;

    /**
     * 变焦参数
     */
    @TableField("zoom_param")
    private BigDecimal zoomParam;

    /**
     * 触发器类型
     */
    @TableField("trigger_type")
    private String triggerType;

    /**
     * 触发器参数
     */
    @TableField("trigger_param")
    private BigDecimal triggerParam;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;
}