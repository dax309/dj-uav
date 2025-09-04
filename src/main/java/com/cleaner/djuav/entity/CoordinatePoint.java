package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 坐标点表实体类
 * 
 * @author SOLO Coding
 * @since 2024-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("coordinate_points")
public class CoordinatePoint {

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
     * 坐标点类型(polygon, linestring等)
     */
    @TableField("point_type")
    private String pointType;

    /**
     * 点序号
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
     * 高度(米)
     */
    @TableField("height")
    private BigDecimal height;

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
}