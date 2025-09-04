package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 航线主表实体类
 * 
 * @author SOLO Coding
 * @since 2024-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("uav_routes")
public class UavRoute {

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 航线名称
     */
    @TableField("route_name")
    private String routeName;

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
     * 全局航线高度(米)
     */
    @TableField("global_height")
    private BigDecimal globalHeight;

    /**
     * 全局航线飞行速度(米/秒)
     */
    @TableField("auto_flight_speed")
    private BigDecimal autoFlightSpeed;

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
     * 全局偏航角模式配置
     */
    @TableField(value = "waypoint_heading_config", typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private JsonNode waypointHeadingConfig;

    /**
     * 全局航点转弯模式配置
     */
    @TableField(value = "waypoint_turn_config", typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private JsonNode waypointTurnConfig;

    /**
     * 建图航拍配置参数
     */
    @TableField(value = "mapping_config", typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private JsonNode mappingConfig;

    /**
     * 航线初始动作列表
     */
    @TableField(value = "start_actions", typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private JsonNode startActions;

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
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 软删除标记
     */
    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;

    /**
     * 乐观锁版本号
     */
    @Version
    @TableField("version")
    private Integer version;

    /**
     * 设置航点偏航角参数
     */
    public void setWaypointHeadingParam(JsonNode waypointHeadingParam) {
        this.waypointHeadingConfig = waypointHeadingParam;
    }

    /**
     * 设置航点转弯参数
     */
    public void setWaypointTurnParam(JsonNode waypointTurnParam) {
        this.waypointTurnConfig = waypointTurnParam;
    }
}