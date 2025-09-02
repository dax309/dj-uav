package com.cleaner.djuav.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 航线模板表实体类
 * 对应数据库表：fcp_route_templates
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "fcp_route_templates")
public class RouteTemplate {

    /**
     * 主键ID（雪花算法生成）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 模板类型：waypoint/mapping2d/mapping3d/mappingStrip
     */
    @TableField("template_type")
    private String templateType;

    /**
     * 模板名称
     */
    @TableField("template_name")
    private String templateName;

    /**
     * 模板描述
     */
    @TableField("description")
    private String description;

    /**
     * 默认配置参数
     */
    @TableField(value = "default_config", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> defaultConfig;

    /**
     * 是否启用
     */
    @TableField("is_active")
    private Boolean isActive;

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