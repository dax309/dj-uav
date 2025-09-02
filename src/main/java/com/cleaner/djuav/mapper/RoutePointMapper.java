package com.cleaner.djuav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cleaner.djuav.entity.RoutePoint;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 航线航点表 Mapper 接口
 */
@Mapper
public interface RoutePointMapper extends BaseMapper<RoutePoint> {

    /**
     * 根据航线ID查询所有航点
     * @param routeId 航线ID
     * @return 航点列表
     */
    @Select("SELECT * FROM fcp_route_points WHERE route_id = #{routeId} ORDER BY point_index ASC")
    List<RoutePoint> selectByRouteId(@Param("routeId") Long routeId);

    /**
     * 根据航线ID删除所有航点
     * @param routeId 航线ID
     * @return 删除的记录数
     */
    @Delete("DELETE FROM fcp_route_points WHERE route_id = #{routeId}")
    int deleteByRouteId(@Param("routeId") Long routeId);

    /**
     * 根据航线ID和航点序号查询航点
     * @param routeId 航线ID
     * @param pointIndex 航点序号
     * @return 航点信息
     */
    @Select("SELECT * FROM fcp_route_points WHERE route_id = #{routeId} AND point_index = #{pointIndex}")
    RoutePoint selectByRouteIdAndIndex(@Param("routeId") Long routeId, @Param("pointIndex") Integer pointIndex);

    /**
     * 获取航线的航点总数
     * @param routeId 航线ID
     * @return 航点总数
     */
    @Select("SELECT COUNT(*) FROM fcp_route_points WHERE route_id = #{routeId}")
    int countByRouteId(@Param("routeId") Long routeId);
}