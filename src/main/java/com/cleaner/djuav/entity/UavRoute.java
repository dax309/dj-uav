package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 无人机航线表实体类
 * 对应数据库表：fcp_uav_routes
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "fcp_uav_routes")
public class UavRoute {

    /**
     * 主键ID（雪花算法生成）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 航线名称
     */
    @TableField("route_name")
    private String routeName;

    /**
     * 航线模板类型：waypoint/mapping2d/mapping3d/mappingStrip
     */
    @TableField("template_type")
    private String templateType;

    /**
     * 无人机类型（枚举值）
     */
    @TableField("drone_type")
    private String droneType;

    /**
     * 负载类型（枚举值）
     */
    @TableField("payload_type")
    private String payloadType;

    /**
     * 无人机详细信息（可选）
     */
    @TableField(value = "drone_info", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> droneInfo;

    /**
     * 负载详细信息（可选）
     */
    @TableField(value = "payload_info", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> payloadInfo;

    /**
     * 负载挂载位置
     */
    @TableField("payload_position")
    private Integer payloadPosition;

    /**
     * 图片存储格式
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
     * 全局航线高度（米）
     */
    @TableField("global_height")
    private BigDecimal globalHeight;

    /**
     * 全局飞行速度（米/秒）
     */
    @TableField("auto_flight_speed")
    private BigDecimal autoFlightSpeed;

    /**
     * 云台俯仰角控制模式
     */
    @TableField("gimbal_pitch_mode")
    private String gimbalPitchMode;

    /**
     * 参考起飞点坐标
     */
    @TableField("take_off_ref_point")
    private String takeOffRefPoint;

    /**
     * 全局偏航角模式
     */
    @TableField("waypoint_heading_mode")
    private String waypointHeadingMode;

    /**
     * 全局偏航角度
     */
    @TableField("waypoint_heading_angle")
    private BigDecimal waypointHeadingAngle;

    /**
     * 全局兴趣点坐标
     */
    @TableField("waypoint_poi_point")
    private String waypointPoiPoint;

    /**
     * 全局航点转弯模式
     */
    @TableField("waypoint_turn_mode")
    private String waypointTurnMode;

    /**
     * 全局转弯阻尼距离
     */
    @TableField("waypoint_turn_damping_dist")
    private BigDecimal waypointTurnDampingDist;

    /**
     * 是否使用直线飞行
     */
    @TableField("use_straight_line")
    private Integer useStraightLine;

    /**
     * 航线描述
     */
    @TableField("description")
    private String description;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}