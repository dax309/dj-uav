package com.cleaner.djuav.util;

import com.cleaner.djuav.domain.*;
import com.cleaner.djuav.domain.kml.KmlParams;
import com.cleaner.djuav.domain.kml.RoutePointInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 航线数据转换工具类
 * Author: Cleaner
 * Date: 2024/12/22
 */
@Component
public class RouteDataConverter {

    /**
     * 将UavRouteReq转换为KmlParams
     */
    public KmlParams convertToKmlParams(UavRouteReq uavRouteReq) {
        KmlParams kmlParams = new KmlParams();
        
        // 基础信息转换
        kmlParams.setTemplateType(uavRouteReq.getTemplateType());
        kmlParams.setDroneType(uavRouteReq.getDroneType());
        kmlParams.setSubDroneType(uavRouteReq.getSubDroneType());
        kmlParams.setPayloadType(uavRouteReq.getPayloadType());
        kmlParams.setPayloadPosition(uavRouteReq.getPayloadPosition());
        kmlParams.setImageFormat(uavRouteReq.getImageFormat());
        kmlParams.setFinishAction(uavRouteReq.getFinishAction());
        kmlParams.setExitOnRcLostAction(uavRouteReq.getExitOnRcLostAction());
        kmlParams.setGlobalHeight(uavRouteReq.getGlobalHeight());
        kmlParams.setAutoFlightSpeed(uavRouteReq.getAutoFlightSpeed());
        kmlParams.setWaypointHeadingReq(uavRouteReq.getWaypointHeadingReq());
        kmlParams.setWaypointTurnReq(uavRouteReq.getWaypointTurnReq());
        kmlParams.setGimbalPitchMode(uavRouteReq.getGimbalPitchMode());
        kmlParams.setTakeOffRefPoint(uavRouteReq.getTakeOffRefPoint());
        kmlParams.setMappingTypeReq(uavRouteReq.getMappingTypeReq());
        kmlParams.setStartActionList(uavRouteReq.getStartActionList());
        
        // 航点信息转换
        if (uavRouteReq.getRoutePointList() != null) {
            List<RoutePointInfo> routePointInfoList = uavRouteReq.getRoutePointList().stream()
                    .map(this::convertToRoutePointInfo)
                    .collect(Collectors.toList());
            kmlParams.setRoutePointList(routePointInfoList);
        }
        
        return kmlParams;
    }

    /**
     * 将RoutePointReq转换为RoutePointInfo
     */
    private RoutePointInfo convertToRoutePointInfo(RoutePointReq routePointReq) {
        RoutePointInfo routePointInfo = new RoutePointInfo();
        
        routePointInfo.setRoutePointIndex(routePointReq.getRoutePointIndex());
        routePointInfo.setLongitude(routePointReq.getLongitude());
        routePointInfo.setLatitude(routePointReq.getLatitude());
        routePointInfo.setHeight(routePointReq.getHeight());
        routePointInfo.setSpeed(routePointReq.getSpeed());
        routePointInfo.setWaypointHeadingReq(routePointReq.getWaypointHeadingReq());
        routePointInfo.setWaypointTurnReq(routePointReq.getWaypointTurnReq());
        routePointInfo.setGimbalPitchAngle(routePointReq.getGimbalPitchAngle());
        routePointInfo.setActions(routePointReq.getActions());
        routePointInfo.setTimeInterval(routePointReq.getTimeInterval());
        routePointInfo.setDistanceInterval(routePointReq.getDistanceInterval());
        routePointInfo.setEndIntervalRouteIndex(routePointReq.getEndIntervalRouteIndex());
        
        return routePointInfo;
    }
}
