package com.cleaner.djuav.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cleaner.djuav.entity.PointAction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 航点动作表 Mapper 接口
 */
@Mapper
public interface PointActionMapper extends BaseMapper<PointAction> {

    /**
     * 根据航点ID查询所有动作
     * @param pointId 航点ID
     * @return 动作列表
     */
    @Select("SELECT * FROM fcp_point_actions WHERE point_id = #{pointId} ORDER BY action_index ASC")
    List<PointAction> selectByPointId(@Param("pointId") Long pointId);

    /**
     * 根据航点ID删除所有动作
     * @param pointId 航点ID
     * @return 删除的记录数
     */
    @Delete("DELETE FROM fcp_point_actions WHERE point_id = #{pointId}")
    int deleteByPointId(@Param("pointId") Long pointId);

    /**
     * 根据航线ID删除所有相关动作
     * @param routeId 航线ID
     * @return 删除的记录数
     */
    @Delete("DELETE FROM fcp_point_actions WHERE point_id IN (SELECT id FROM fcp_route_points WHERE route_id = #{routeId})")
    int deleteByRouteId(@Param("routeId") Long routeId);

    /**
     * 根据航点ID和动作序号查询动作
     * @param pointId 航点ID
     * @param actionIndex 动作序号
     * @return 动作信息
     */
    @Select("SELECT * FROM fcp_point_actions WHERE point_id = #{pointId} AND action_index = #{actionIndex}")
    PointAction selectByPointIdAndIndex(@Param("pointId") Long pointId, @Param("actionIndex") Integer actionIndex);

    /**
     * 获取航点的动作总数
     * @param pointId 航点ID
     * @return 动作总数
     */
    @Select("SELECT COUNT(*) FROM fcp_point_actions WHERE point_id = #{pointId}")
    int countByPointId(@Param("pointId") Long pointId);
}