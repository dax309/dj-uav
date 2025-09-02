package com.cleaner.djuav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cleaner.djuav.entity.MappingConfig;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 建图配置表 Mapper 接口
 */
@Mapper
public interface MappingConfigMapper extends BaseMapper<MappingConfig> {

    /**
     * 根据航线ID查询建图配置
     * @param routeId 航线ID
     * @return 建图配置信息
     */
    @Select("SELECT * FROM fcp_mapping_configs WHERE route_id = #{routeId}")
    MappingConfig selectByRouteId(@Param("routeId") Long routeId);

    /**
     * 根据航线ID删除建图配置
     * @param routeId 航线ID
     * @return 删除的记录数
     */
    @Delete("DELETE FROM fcp_mapping_configs WHERE route_id = #{routeId}")
    int deleteByRouteId(@Param("routeId") Long routeId);

    /**
     * 根据采集方式查询建图配置列表
     * @param collectionMethod 采集方式
     * @return 建图配置列表
     */
    @Select("SELECT * FROM fcp_mapping_configs WHERE collection_method = #{collectionMethod} ORDER BY created_at DESC")
    List<MappingConfig> selectByCollectionMethod(@Param("collectionMethod") String collectionMethod);

    /**
     * 根据镜头类型查询建图配置列表
     * @param lensType 镜头类型
     * @return 建图配置列表
     */
    @Select("SELECT * FROM fcp_mapping_configs WHERE lens_type = #{lensType} ORDER BY created_at DESC")
    List<MappingConfig> selectByLensType(@Param("lensType") String lensType);

    /**
     * 检查航线是否存在建图配置
     * @param routeId 航线ID
     * @return 是否存在
     */
    @Select("SELECT COUNT(*) > 0 FROM fcp_mapping_configs WHERE route_id = #{routeId}")
    boolean existsByRouteId(@Param("routeId") Long routeId);
}