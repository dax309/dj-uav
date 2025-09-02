package com.cleaner.djuav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cleaner.djuav.entity.UavRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 无人机航线表 Mapper 接口
 */
@Mapper
public interface UavRouteMapper extends BaseMapper<UavRoute> {

    /**
     * 根据航线名称查询航线
     * @param routeName 航线名称
     * @return 航线信息
     */
    @Select("SELECT * FROM fcp_uav_routes WHERE route_name = #{routeName}")
    UavRoute selectByRouteName(@Param("routeName") String routeName);

    /**
     * 根据模板类型查询航线列表
     * @param templateType 模板类型
     * @return 航线列表
     */
    @Select("SELECT * FROM fcp_uav_routes WHERE template_type = #{templateType} ORDER BY created_at DESC")
    List<UavRoute> selectByTemplateType(@Param("templateType") String templateType);

    /**
     * 根据无人机类型查询航线列表
     * @param droneType 无人机类型
     * @return 航线列表
     */
    @Select("SELECT * FROM fcp_uav_routes WHERE drone_type = #{droneType} ORDER BY created_at DESC")
    List<UavRoute> selectByDroneType(@Param("droneType") String droneType);
}