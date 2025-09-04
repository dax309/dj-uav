package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 无人机航线建图参数表
 * Author: Cleaner
 * Date: 2024/12/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("uav_route_mapping")
public class UavRouteMapping implements Serializable {

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
     * 采集方式
     */
    @TableField("collection_method")
    private String collectionMethod;

    /**
     * 镜头类型
     */
    @TableField("lens_type")
    private String lensType;

    /**
     * 航向重叠率
     */
    @TableField("overlap_h")
    private Integer overlapH;

    /**
     * 旁向重叠率
     */
    @TableField("overlap_w")
    private Integer overlapW;

    /**
     * 是否开启高程优化
     */
    @TableField("elevation_optimize_enable")
    private Integer elevationOptimizeEnable;

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
     * 测区外扩距离
     */
    @TableField("margin")
    private String margin;

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
