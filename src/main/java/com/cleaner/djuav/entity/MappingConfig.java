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
 * 建图配置表实体类
 * 对应数据库表：fcp_mapping_configs
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "fcp_mapping_configs")
public class MappingConfig {

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
     * 采集方式：camera/lidar
     */
    @TableField("collection_method")
    private String collectionMethod;

    /**
     * 镜头类型
     */
    @TableField("lens_type")
    private String lensType;

    /**
     * 航向重叠率（%）
     */
    @TableField("overlap_h")
    private Integer overlapH;

    /**
     * 旁向重叠率（%）
     */
    @TableField("overlap_w")
    private Integer overlapW;

    /**
     * 高程优化
     */
    @TableField("elevation_optimize_enable")
    private Boolean elevationOptimizeEnable;

    /**
     * 拍照模式
     */
    @TableField("shoot_type")
    private String shootType;

    /**
     * 航线方向
     */
    @TableField("direction")
    private String direction;

    /**
     * 测区外扩距离（米）
     */
    @TableField("margin")
    private BigDecimal margin;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;
}