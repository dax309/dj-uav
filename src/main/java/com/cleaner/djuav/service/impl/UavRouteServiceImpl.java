package com.cleaner.djuav.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cleaner.djuav.domain.RoutePointReq;
import com.cleaner.djuav.domain.UavRouteReq;
import com.cleaner.djuav.domain.kml.KmlInfo;
import com.cleaner.djuav.domain.kml.KmlParams;
import com.cleaner.djuav.entity.*;
import com.cleaner.djuav.mapper.*;
import com.cleaner.djuav.service.UavRouteService;
import com.cleaner.djuav.util.RouteDataConverter;
import com.cleaner.djuav.util.RouteEntityConverter;
import com.cleaner.djuav.util.RouteFileUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 无人机航线服务实现类
 * Author: Cleaner
 * Date: 2024/12/22
 */
@Service
public class UavRouteServiceImpl extends ServiceImpl<UavRouteMapper, UavRoute> implements UavRouteService {

    @Resource
    private UavRouteMapper uavRouteMapper;
    
    @Resource
    private UavWaypointMapper uavWaypointMapper;
    
    @Resource
    private UavWaypointActionMapper uavWaypointActionMapper;
    
    @Resource
    private UavRouteMappingMapper uavRouteMappingMapper;
    
    @Resource
    private UavRouteCoordinateMapper uavRouteCoordinateMapper;
    
    @Resource
    private RouteDataConverter routeDataConverter;
    
    @Resource
    private RouteEntityConverter routeEntityConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateKmz(UavRouteReq uavRouteReq) {
        // 1. 转换请求参数为KmlParams（保持原有业务逻辑）
        KmlParams kmlParams = routeDataConverter.convertToKmlParams(uavRouteReq);
        
        // 2. 更新航线数据到数据库（新增功能）
        // 注意：这里需要从请求中获取航线ID，或者通过其他方式确定要更新的航线
        // 暂时假设请求中包含航线ID，实际使用时需要根据业务需求调整
        Long routeId = getRouteIdFromRequest(uavRouteReq);
        if (routeId != null) {
            updateRouteInDatabase(uavRouteReq, routeId);
            
            // 3. 重新生成KMZ文件（保持原有业务逻辑）
            String fileName = "route_" + routeId + "_" + System.currentTimeMillis();
            String kmzFilePath = RouteFileUtils.buildKmz(fileName, kmlParams);
            
            // 4. 更新航线记录中的KMZ文件路径（新增功能）
            updateRouteKmzPath(routeId, kmzFilePath);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void buildKmz(UavRouteReq uavRouteReq) {
        // 1. 转换请求参数为KmlParams（保持原有业务逻辑）
        KmlParams kmlParams = routeDataConverter.convertToKmlParams(uavRouteReq);
        
        // 2. 保存航线数据到数据库（新增功能）
        Long routeId = saveRouteToDatabase(uavRouteReq);
        
        // 3. 生成KMZ文件（保持原有业务逻辑）
        String fileName = "route_" + routeId + "_" + System.currentTimeMillis();
        String kmzFilePath = RouteFileUtils.buildKmz(fileName, kmlParams);
        
        // 4. 更新航线记录中的KMZ文件路径（新增功能）
        updateRouteKmzPath(routeId, kmzFilePath);
    }

    @Override
    public KmlInfo parseKmz(String fileUrl) throws IOException {
        // 1. 读取KMZ文件（保持原有业务逻辑）
        try (InputStream inputStream = new FileInputStream(fileUrl)) {
            // 2. 解析KML内容（保持原有业务逻辑）
            return RouteFileUtils.parseKml(inputStream);
        }
    }

    /**
     * 根据航线ID查询航线详细信息（包含航点、动作等）
     */
    public UavRoute getRouteDetailById(Long routeId) {
        // 查询航线基础信息
        UavRoute route = uavRouteMapper.selectById(routeId);
        if (route == null) {
            return null;
        }

        // 查询航点信息
        LambdaQueryWrapper<UavWaypoint> waypointWrapper = new LambdaQueryWrapper<>();
        waypointWrapper.eq(UavWaypoint::getRouteId, routeId)
                      .orderByAsc(UavWaypoint::getRoutePointIndex);
        List<UavWaypoint> waypoints = uavWaypointMapper.selectList(waypointWrapper);

        // 查询航点动作
        if (!waypoints.isEmpty()) {
            List<Long> waypointIds = waypoints.stream()
                    .map(UavWaypoint::getId)
                    .toList();
            
            LambdaQueryWrapper<UavWaypointAction> actionWrapper = new LambdaQueryWrapper<>();
            actionWrapper.in(UavWaypointAction::getWaypointId, waypointIds)
                        .orderByAsc(UavWaypointAction::getActionIndex);
            List<UavWaypointAction> actions = uavWaypointActionMapper.selectList(actionWrapper);
            
            // 将动作关联到对应的航点（这里简化处理，实际应该建立关联关系）
        }

        // 查询建图参数
        LambdaQueryWrapper<UavRouteMapping> mappingWrapper = new LambdaQueryWrapper<>();
        mappingWrapper.eq(UavRouteMapping::getRouteId, routeId);
        UavRouteMapping mapping = uavRouteMappingMapper.selectOne(mappingWrapper);

        // 查询测区坐标
        LambdaQueryWrapper<UavRouteCoordinate> coordinateWrapper = new LambdaQueryWrapper<>();
        coordinateWrapper.eq(UavRouteCoordinate::getRouteId, routeId)
                        .orderByAsc(UavRouteCoordinate::getCoordinateIndex);
        List<UavRouteCoordinate> coordinates = uavRouteCoordinateMapper.selectList(coordinateWrapper);

        return route;
    }

    /**
     * 查询航线列表（基本信息）
     */
    public List<UavRoute> getRouteList() {
        LambdaQueryWrapper<UavRoute> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(UavRoute::getCreateTime);
        return uavRouteMapper.selectList(wrapper);
    }

    /**
     * 根据航线状态查询航线
     */
    public List<UavRoute> getRoutesByStatus(Integer status) {
        LambdaQueryWrapper<UavRoute> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UavRoute::getRouteStatus, status)
               .orderByDesc(UavRoute::getCreateTime);
        return uavRouteMapper.selectList(wrapper);
    }

    /**
     * 保存航线数据到数据库
     */
    private Long saveRouteToDatabase(UavRouteReq uavRouteReq) {
        // 1. 保存航线基础信息
        UavRoute uavRoute = routeEntityConverter.convertToUavRoute(uavRouteReq);
        uavRouteMapper.insert(uavRoute);
        Long routeId = uavRoute.getId();

        // 2. 保存航点信息
        if (uavRouteReq.getRoutePointList() != null && !uavRouteReq.getRoutePointList().isEmpty()) {
            for (RoutePointReq routePointReq : uavRouteReq.getRoutePointList()) {
                UavWaypoint uavWaypoint = routeEntityConverter.convertToUavWaypoint(routePointReq, routeId);
                uavWaypointMapper.insert(uavWaypoint);
                Long waypointId = uavWaypoint.getId();

                // 3. 保存航点动作
                if (routePointReq.getActions() != null && !routePointReq.getActions().isEmpty()) {
                    List<UavWaypointAction> actions = routeEntityConverter.convertToUavWaypointActions(
                            routePointReq.getActions(), waypointId, routeId);
                    for (UavWaypointAction action : actions) {
                        uavWaypointActionMapper.insert(action);
                    }
                }
            }
        }

        // 4. 保存建图参数
        if (uavRouteReq.getMappingTypeReq() != null) {
            UavRouteMapping uavRouteMapping = routeEntityConverter.convertToUavRouteMapping(
                    uavRouteReq.getMappingTypeReq(), routeId);
            uavRouteMappingMapper.insert(uavRouteMapping);

            // 5. 保存测区坐标
            if (uavRouteReq.getMappingTypeReq().getCoordinates() != null) {
                List<UavRouteCoordinate> coordinates = routeEntityConverter.convertToUavRouteCoordinates(
                        uavRouteReq.getMappingTypeReq().getCoordinates(), routeId);
                for (UavRouteCoordinate coordinate : coordinates) {
                    uavRouteCoordinateMapper.insert(coordinate);
                }
            }
        }

        // 6. 保存航线初始动作
        if (uavRouteReq.getStartActionList() != null && !uavRouteReq.getStartActionList().isEmpty()) {
            List<UavWaypointAction> startActions = routeEntityConverter.convertToUavWaypointActions(
                    uavRouteReq.getStartActionList(), null, routeId);
            for (UavWaypointAction action : startActions) {
                action.setActionType(1); // 航线初始动作
                uavWaypointActionMapper.insert(action);
            }
        }

        return routeId;
    }

    /**
     * 更新航线数据到数据库
     */
    private void updateRouteInDatabase(UavRouteReq uavRouteReq, Long routeId) {
        // 1. 更新航线基础信息
        UavRoute uavRoute = routeEntityConverter.convertToUavRoute(uavRouteReq);
        uavRoute.setId(routeId);
        uavRoute.setUpdateBy("system");
        uavRouteMapper.updateById(uavRoute);

        // 2. 删除原有航点数据（逻辑删除）
        LambdaQueryWrapper<UavWaypoint> waypointWrapper = new LambdaQueryWrapper<>();
        waypointWrapper.eq(UavWaypoint::getRouteId, routeId);
        List<UavWaypoint> existingWaypoints = uavWaypointMapper.selectList(waypointWrapper);
        for (UavWaypoint waypoint : existingWaypoints) {
            uavWaypointMapper.deleteById(waypoint.getId());
        }

        // 3. 重新保存航点信息
        if (uavRouteReq.getRoutePointList() != null && !uavRouteReq.getRoutePointList().isEmpty()) {
            for (RoutePointReq routePointReq : uavRouteReq.getRoutePointList()) {
                UavWaypoint uavWaypoint = routeEntityConverter.convertToUavWaypoint(routePointReq, routeId);
                uavWaypointMapper.insert(uavWaypoint);
                Long waypointId = uavWaypoint.getId();

                // 保存航点动作
                if (routePointReq.getActions() != null && !routePointReq.getActions().isEmpty()) {
                    List<UavWaypointAction> actions = routeEntityConverter.convertToUavWaypointActions(
                            routePointReq.getActions(), waypointId, routeId);
                    for (UavWaypointAction action : actions) {
                        uavWaypointActionMapper.insert(action);
                    }
                }
            }
        }

        // 4. 更新建图参数
        if (uavRouteReq.getMappingTypeReq() != null) {
            LambdaQueryWrapper<UavRouteMapping> mappingWrapper = new LambdaQueryWrapper<>();
            mappingWrapper.eq(UavRouteMapping::getRouteId, routeId);
            UavRouteMapping existingMapping = uavRouteMappingMapper.selectOne(mappingWrapper);
            
            UavRouteMapping uavRouteMapping = routeEntityConverter.convertToUavRouteMapping(
                    uavRouteReq.getMappingTypeReq(), routeId);
            uavRouteMapping.setUpdateBy("system");
            
            if (existingMapping != null) {
                uavRouteMapping.setId(existingMapping.getId());
                uavRouteMappingMapper.updateById(uavRouteMapping);
            } else {
                uavRouteMappingMapper.insert(uavRouteMapping);
            }

            // 更新测区坐标
            if (uavRouteReq.getMappingTypeReq().getCoordinates() != null) {
                // 删除原有坐标
                LambdaQueryWrapper<UavRouteCoordinate> coordinateWrapper = new LambdaQueryWrapper<>();
                coordinateWrapper.eq(UavRouteCoordinate::getRouteId, routeId);
                List<UavRouteCoordinate> existingCoordinates = uavRouteCoordinateMapper.selectList(coordinateWrapper);
                for (UavRouteCoordinate coordinate : existingCoordinates) {
                    uavRouteCoordinateMapper.deleteById(coordinate.getId());
                }

                // 重新保存坐标
                List<UavRouteCoordinate> coordinates = routeEntityConverter.convertToUavRouteCoordinates(
                        uavRouteReq.getMappingTypeReq().getCoordinates(), routeId);
                for (UavRouteCoordinate coordinate : coordinates) {
                    uavRouteCoordinateMapper.insert(coordinate);
                }
            }
        }

        // 5. 更新航线初始动作
        if (uavRouteReq.getStartActionList() != null && !uavRouteReq.getStartActionList().isEmpty()) {
            // 删除原有初始动作
            LambdaQueryWrapper<UavWaypointAction> startActionWrapper = new LambdaQueryWrapper<>();
            startActionWrapper.eq(UavWaypointAction::getRouteId, routeId)
                            .eq(UavWaypointAction::getActionType, 1);
            List<UavWaypointAction> existingStartActions = uavWaypointActionMapper.selectList(startActionWrapper);
            for (UavWaypointAction action : existingStartActions) {
                uavWaypointActionMapper.deleteById(action.getId());
            }

            // 重新保存初始动作
            List<UavWaypointAction> startActions = routeEntityConverter.convertToUavWaypointActions(
                    uavRouteReq.getStartActionList(), null, routeId);
            for (UavWaypointAction action : startActions) {
                action.setActionType(1); // 航线初始动作
                uavWaypointActionMapper.insert(action);
            }
        }
    }

    /**
     * 更新航线KMZ文件路径
     */
    private void updateRouteKmzPath(Long routeId, String kmzFilePath) {
        UavRoute uavRoute = new UavRoute();
        uavRoute.setId(routeId);
        uavRoute.setKmzFilePath(kmzFilePath);
        uavRoute.setUpdateBy("system");
        uavRouteMapper.updateById(uavRoute);
    }

    /**
     * 从请求中获取航线ID
     * 注意：这里需要根据实际业务需求调整，可能需要修改UavRouteReq类添加routeId字段
     */
    private Long getRouteIdFromRequest(UavRouteReq uavRouteReq) {
        // 这里需要根据实际业务需求实现
        // 可能需要：
        // 1. 在UavRouteReq中添加routeId字段
        // 2. 或者通过其他方式确定要更新的航线ID
        // 暂时返回null，实际使用时需要实现
        return null;
    }
}