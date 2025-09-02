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
 * 测区坐标表实体类
 * 对应数据库表：fcp_survey_area_coordinates
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "fcp_survey_area_coordinates")
public class SurveyAreaCoordinate {

    /**
     * 主键ID（雪花算法生成）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 建图配置ID
     */
    @TableField("mapping_config_id")
    private Long mappingConfigId;

    /**
     * 坐标点序号
     */
    @TableField("coordinate_index")
    private Integer coordinateIndex;

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
     * 高度（米）
     */
    @TableField("height")
    private BigDecimal height;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;
}