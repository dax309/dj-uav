package com.cleaner.djuav.util;

import com.cleaner.djuav.domain.*;
import com.cleaner.djuav.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 航线数据库实体转换工具类
 * Author: Cleaner
 * Date: 2024/12/22
 */
@Component
public class RouteEntityConverter {

    /**
     * 将UavRouteReq转换为UavRoute实体
     */
    public UavRoute convertToUavRoute(UavRouteReq uavRouteReq) {
        UavRoute uavRoute = new UavRoute();
        
        // 基础信息转换
        uavRoute.setTemplateType(uavRouteReq.getTemplateType());
        uavRoute.setDroneType(uavRouteReq.getDroneType());
        uavRoute.setSubDroneType(uavRouteReq.getSubDroneType());
        uavRoute.setPayloadType(uavRouteReq.getPayloadType());
        uavRoute.setPayloadPosition(uavRouteReq.getPayloadPosition());
        uavRoute.setImageFormat(uavRouteReq.getImageFormat());
        uavRoute.setFinishAction(uavRouteReq.getFinishAction());
        uavRoute.setExitOnRcLostAction(uavRouteReq.getExitOnRcLostAction());
        uavRoute.setGlobalHeight(uavRouteReq.getGlobalHeight());
        uavRoute.setAutoFlightSpeed(uavRouteReq.getAutoFlightSpeed());
        uavRoute.setGimbalPitchMode(uavRouteReq.getGimbalPitchMode());
        uavRoute.setTakeOffRefPoint(uavRouteReq.getTakeOffRefPoint());
        
        // 全局偏航角参数转换
        if (uavRouteReq.getWaypointHeadingReq() != null) {
            uavRoute.setWaypointHeadingMode(uavRouteReq.getWaypointHeadingReq().getWaypointHeadingMode());
            uavRoute.setWaypointHeadingAngle(uavRouteReq.getWaypointHeadingReq().getWaypointHeadingAngle());
            uavRoute.setWaypointPoiPoint(uavRouteReq.getWaypointHeadingReq().getWaypointPoiPoint());
        }
        
        // 全局转弯参数转换
        if (uavRouteReq.getWaypointTurnReq() != null) {
            uavRoute.setWaypointTurnMode(uavRouteReq.getWaypointTurnReq().getWaypointTurnMode());
            uavRoute.setWaypointTurnDampingDist(uavRouteReq.getWaypointTurnReq().getWaypointTurnDampingDist());
            uavRoute.setUseStraightLine(uavRouteReq.getWaypointTurnReq().getUseStraightLine());
        }
        
        // 设置默认值
        uavRoute.setRouteStatus(0); // 草稿状态
        uavRoute.setCreateBy("system"); // 默认创建人
        
        return uavRoute;
    }

    /**
     * 将RoutePointReq转换为UavWaypoint实体
     */
    public UavWaypoint convertToUavWaypoint(RoutePointReq routePointReq, Long routeId) {
        UavWaypoint uavWaypoint = new UavWaypoint();
        
        uavWaypoint.setRouteId(routeId);
        uavWaypoint.setRoutePointIndex(routePointReq.getRoutePointIndex());
        uavWaypoint.setLongitude(routePointReq.getLongitude());
        uavWaypoint.setLatitude(routePointReq.getLatitude());
        uavWaypoint.setHeight(routePointReq.getHeight());
        uavWaypoint.setSpeed(routePointReq.getSpeed());
        uavWaypoint.setGimbalPitchAngle(routePointReq.getGimbalPitchAngle());
        uavWaypoint.setTimeInterval(routePointReq.getTimeInterval());
        uavWaypoint.setDistanceInterval(routePointReq.getDistanceInterval());
        uavWaypoint.setEndIntervalRouteIndex(routePointReq.getEndIntervalRouteIndex());
        
        // 航点偏航角参数转换
        if (routePointReq.getWaypointHeadingReq() != null) {
            uavWaypoint.setWaypointHeadingMode(routePointReq.getWaypointHeadingReq().getWaypointHeadingMode());
            uavWaypoint.setWaypointHeadingAngle(routePointReq.getWaypointHeadingReq().getWaypointHeadingAngle());
            uavWaypoint.setWaypointPoiPoint(routePointReq.getWaypointHeadingReq().getWaypointPoiPoint());
        }
        
        // 航点转弯参数转换
        if (routePointReq.getWaypointTurnReq() != null) {
            uavWaypoint.setWaypointTurnMode(routePointReq.getWaypointTurnReq().getWaypointTurnMode());
            uavWaypoint.setWaypointTurnDampingDist(routePointReq.getWaypointTurnReq().getWaypointTurnDampingDist());
            uavWaypoint.setUseStraightLine(routePointReq.getWaypointTurnReq().getUseStraightLine());
        }
        
        uavWaypoint.setCreateBy("system");
        
        return uavWaypoint;
    }

    /**
     * 将PointActionReq转换为UavWaypointAction实体
     */
    public UavWaypointAction convertToUavWaypointAction(PointActionReq pointActionReq, Long waypointId, Long routeId) {
        UavWaypointAction uavWaypointAction = new UavWaypointAction();
        
        uavWaypointAction.setWaypointId(waypointId);
        uavWaypointAction.setRouteId(routeId);
        uavWaypointAction.setActionIndex(pointActionReq.getActionIndex());
        uavWaypointAction.setHoverTime(pointActionReq.getHoverTime());
        uavWaypointAction.setAircraftHeading(pointActionReq.getAircraftHeading());
        uavWaypointAction.setTakePhotoType(pointActionReq.getTakePhotoType());
        uavWaypointAction.setUseGlobalImageFormat(pointActionReq.getUseGlobalImageFormat());
        uavWaypointAction.setImageFormat(pointActionReq.getImageFormat());
        uavWaypointAction.setGimbalYawRotateAngle(pointActionReq.getGimbalYawRotateAngle());
        uavWaypointAction.setGimbalPitchRotateAngle(pointActionReq.getGimbalPitchRotateAngle());
        uavWaypointAction.setZoom(pointActionReq.getZoom());
        uavWaypointAction.setStartRecord(pointActionReq.getStartRecord());
        uavWaypointAction.setStopRecord(pointActionReq.getStopRecord());
        uavWaypointAction.setActionType(0); // 航点动作
        uavWaypointAction.setCreateBy("system");
        
        return uavWaypointAction;
    }

    /**
     * 将MappingTypeReq转换为UavRouteMapping实体
     */
    public UavRouteMapping convertToUavRouteMapping(MappingTypeReq mappingTypeReq, Long routeId) {
        UavRouteMapping uavRouteMapping = new UavRouteMapping();
        
        uavRouteMapping.setRouteId(routeId);
        uavRouteMapping.setCollectionMethod(mappingTypeReq.getCollectionMethod());
        uavRouteMapping.setLensType(mappingTypeReq.getLensType());
        uavRouteMapping.setOverlapH(mappingTypeReq.getOverlapH());
        uavRouteMapping.setOverlapW(mappingTypeReq.getOverlapW());
        uavRouteMapping.setElevationOptimizeEnable(mappingTypeReq.getElevationOptimizeEnable());
        uavRouteMapping.setShootType(mappingTypeReq.getShootType());
        uavRouteMapping.setDirection(mappingTypeReq.getDirection());
        uavRouteMapping.setMargin(mappingTypeReq.getMargin());
        uavRouteMapping.setCreateBy("system");
        
        return uavRouteMapping;
    }

    /**
     * 将CoordinatePointReq转换为UavRouteCoordinate实体
     */
    public UavRouteCoordinate convertToUavRouteCoordinate(CoordinatePointReq coordinatePointReq, Long routeId, Integer coordinateIndex) {
        UavRouteCoordinate uavRouteCoordinate = new UavRouteCoordinate();
        
        uavRouteCoordinate.setRouteId(routeId);
        uavRouteCoordinate.setCoordinateIndex(coordinateIndex);
        uavRouteCoordinate.setLongitude(coordinatePointReq.getLongitude());
        uavRouteCoordinate.setLatitude(coordinatePointReq.getLatitude());
        uavRouteCoordinate.setHeight(coordinatePointReq.getHeight());
        uavRouteCoordinate.setCreateBy("system");
        
        return uavRouteCoordinate;
    }

    /**
     * 批量转换航点动作
     */
    public List<UavWaypointAction> convertToUavWaypointActions(List<PointActionReq> pointActionReqs, Long waypointId, Long routeId) {
        if (pointActionReqs == null || pointActionReqs.isEmpty()) {
            return List.of();
        }
        
        return pointActionReqs.stream()
                .map(actionReq -> convertToUavWaypointAction(actionReq, waypointId, routeId))
                .collect(Collectors.toList());
    }

    /**
     * 批量转换测区坐标
     */
    public List<UavRouteCoordinate> convertToUavRouteCoordinates(List<CoordinatePointReq> coordinatePointReqs, Long routeId) {
        if (coordinatePointReqs == null || coordinatePointReqs.isEmpty()) {
            return List.of();
        }
        
        List<UavRouteCoordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < coordinatePointReqs.size(); i++) {
            coordinates.add(convertToUavRouteCoordinate(coordinatePointReqs.get(i), routeId, i));
        }
        return coordinates;
    }
}
