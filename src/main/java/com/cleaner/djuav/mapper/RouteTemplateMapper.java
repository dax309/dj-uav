package com.cleaner.djuav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cleaner.djuav.entity.RouteTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 航线模板表 Mapper 接口
 */
@Mapper
public interface RouteTemplateMapper extends BaseMapper<RouteTemplate> {

    /**
     * 根据模板类型查询模板列表
     * @param templateType 模板类型
     * @return 模板列表
     */
    @Select("SELECT * FROM fcp_route_templates WHERE template_type = #{templateType} AND is_active = true ORDER BY created_at DESC")
    List<RouteTemplate> selectByTemplateType(@Param("templateType") String templateType);

    /**
     * 根据模板名称查询模板
     * @param templateName 模板名称
     * @return 模板信息
     */
    @Select("SELECT * FROM fcp_route_templates WHERE template_name = #{templateName} AND is_active = true")
    RouteTemplate selectByTemplateName(@Param("templateName") String templateName);

    /**
     * 查询所有启用的模板
     * @return 模板列表
     */
    @Select("SELECT * FROM fcp_route_templates WHERE is_active = true ORDER BY template_type, created_at DESC")
    List<RouteTemplate> selectAllActive();

    /**
     * 启用或禁用模板
     * @param id 模板ID
     * @param isActive 是否启用
     * @return 更新的记录数
     */
    @Update("UPDATE fcp_route_templates SET is_active = #{isActive}, updated_at = NOW() WHERE id = #{id}")
    int updateActiveStatus(@Param("id") Long id, @Param("isActive") Boolean isActive);

    /**
     * 检查模板名称是否存在
     * @param templateName 模板名称
     * @param excludeId 排除的ID（用于更新时检查）
     * @return 是否存在
     */
    @Select("SELECT COUNT(*) > 0 FROM fcp_route_templates WHERE template_name = #{templateName} AND id != #{excludeId}")
    boolean existsByTemplateNameExcludeId(@Param("templateName") String templateName, @Param("excludeId") Long excludeId);

    /**
     * 检查模板名称是否存在
     * @param templateName 模板名称
     * @return 是否存在
     */
    @Select("SELECT COUNT(*) > 0 FROM fcp_route_templates WHERE template_name = #{templateName}")
    boolean existsByTemplateName(@Param("templateName") String templateName);
}