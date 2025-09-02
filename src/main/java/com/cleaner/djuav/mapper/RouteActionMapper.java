package com.cleaner.djuav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cleaner.djuav.entity.RouteAction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 航线动作表 Mapper 接口
 */
@Mapper
public interface RouteActionMapper extends BaseMapper<RouteAction> {

    /**
     * 根据航线ID查询所有动作
     * @param routeId 航线ID
     * @return 动作列表
     */
    @Select("SELECT * FROM fcp_route_actions WHERE route_id = #{routeId} ORDER BY action_index ASC")
    List<RouteAction> selectByRouteId(@Param("routeId") Long routeId);

    /**
     * 根据航线ID和动作类型查询动作
     * @param routeId 航线ID
     * @param actionType 动作类型
     * @return 动作列表
     */
    @Select("SELECT * FROM fcp_route_actions WHERE route_id = #{routeId} AND action_type = #{actionType} ORDER BY action_index ASC")
    List<RouteAction> selectByRouteIdAndType(@Param("routeId") Long routeId, @Param("actionType") String actionType);

    /**
     * 根据航线ID删除所有动作
     * @param routeId 航线ID
     * @return 删除的记录数
     */
    @Delete("DELETE FROM fcp_route_actions WHERE route_id = #{routeId}")
    int deleteByRouteId(@Param("routeId") Long routeId);

    /**
     * 根据航线ID和动作类型删除动作
     * @param routeId 航线ID
     * @param actionType 动作类型
     * @return 删除的记录数
     */
    @Delete("DELETE FROM fcp_route_actions WHERE route_id = #{routeId} AND action_type = #{actionType}")
    int deleteByRouteIdAndType(@Param("routeId") Long routeId, @Param("actionType") String actionType);

    /**
     * 获取航线指定类型动作的总数
     * @param routeId 航线ID
     * @param actionType 动作类型
     * @return 动作总数
     */
    @Select("SELECT COUNT(*) FROM fcp_route_actions WHERE route_id = #{routeId} AND action_type = #{actionType}")
    int countByRouteIdAndType(@Param("routeId") Long routeId, @Param("actionType") String actionType);
}