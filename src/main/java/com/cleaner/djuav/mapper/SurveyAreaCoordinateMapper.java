package com.cleaner.djuav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cleaner.djuav.entity.SurveyAreaCoordinate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 测区坐标表 Mapper 接口
 */
@Mapper
public interface SurveyAreaCoordinateMapper extends BaseMapper<SurveyAreaCoordinate> {

    /**
     * 根据建图配置ID查询所有测区坐标
     * @param mappingConfigId 建图配置ID
     * @return 测区坐标列表
     */
    @Select("SELECT * FROM fcp_survey_area_coordinates WHERE mapping_config_id = #{mappingConfigId} ORDER BY coordinate_index ASC")
    List<SurveyAreaCoordinate> selectByMappingConfigId(@Param("mappingConfigId") Long mappingConfigId);

    /**
     * 根据建图配置ID删除所有测区坐标
     * @param mappingConfigId 建图配置ID
     * @return 删除的记录数
     */
    @Delete("DELETE FROM fcp_survey_area_coordinates WHERE mapping_config_id = #{mappingConfigId}")
    int deleteByMappingConfigId(@Param("mappingConfigId") Long mappingConfigId);

    /**
     * 根据航线ID删除所有相关测区坐标
     * @param routeId 航线ID
     * @return 删除的记录数
     */
    @Delete("DELETE FROM fcp_survey_area_coordinates WHERE mapping_config_id IN (SELECT id FROM fcp_mapping_configs WHERE route_id = #{routeId})")
    int deleteByRouteId(@Param("routeId") Long routeId);

    /**
     * 根据建图配置ID和坐标序号查询测区坐标
     * @param mappingConfigId 建图配置ID
     * @param coordinateIndex 坐标序号
     * @return 测区坐标信息
     */
    @Select("SELECT * FROM fcp_survey_area_coordinates WHERE mapping_config_id = #{mappingConfigId} AND coordinate_index = #{coordinateIndex}")
    SurveyAreaCoordinate selectByMappingConfigIdAndIndex(@Param("mappingConfigId") Long mappingConfigId, @Param("coordinateIndex") Integer coordinateIndex);

    /**
     * 获取建图配置的测区坐标总数
     * @param mappingConfigId 建图配置ID
     * @return 坐标总数
     */
    @Select("SELECT COUNT(*) FROM fcp_survey_area_coordinates WHERE mapping_config_id = #{mappingConfigId}")
    int countByMappingConfigId(@Param("mappingConfigId") Long mappingConfigId);
}