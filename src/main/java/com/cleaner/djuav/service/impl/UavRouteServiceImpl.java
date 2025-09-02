package com.cleaner.djuav.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cleaner.djuav.domain.UavRouteReq;
import com.cleaner.djuav.domain.WaypointHeadingReq;
import com.cleaner.djuav.domain.WaypointTurnReq;
import com.cleaner.djuav.domain.kml.*;
import com.cleaner.djuav.entity.PointAction;
import com.cleaner.djuav.entity.RoutePoint;
import com.cleaner.djuav.entity.UavRoute;
import com.cleaner.djuav.mapper.*;
import com.cleaner.djuav.service.UavRouteService;
import com.cleaner.djuav.util.FileUtils;
import com.cleaner.djuav.util.RouteFileUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author:Cleaner
 * Date: 2024/12/22 10:36
 **/
@Slf4j
@Service
public class UavRouteServiceImpl implements UavRouteService {

    @Resource
    private UavRouteMapper uavRouteMapper;

    @Resource
    private RoutePointMapper routePointMapper;

    @Resource
    private PointActionMapper pointActionMapper;

    @Resource
    private RouteActionMapper routeActionMapper;

    @Resource
    private MappingConfigMapper mappingConfigMapper;

    @Resource
    private SurveyAreaCoordinateMapper surveyAreaCoordinateMapper;

    @Resource
    private RouteTemplateMapper routeTemplateMapper;

    @Override
    public void updateKmz(UavRouteReq uavRouteReq) {
        // 实现航线更新逻辑
        log.info("更新航线，模板类型: {}", uavRouteReq.getTemplateType());
        // 这里可以添加具体的更新逻辑
    }

    @Transactional
    public String updateKmz(String fileUrl, KmlInfo kmlInfo) {
        try {
            // 构建KML参数
            KmlParams kmlParams = buildKmlParams(kmlInfo);

            // 处理航线更新
            handleRouteUpdate(kmlParams);

            // 构建新的KMZ文件
            String newFileUrl = buildKmz(kmlParams);

            // 更新数据库中的航线信息
            updateKmlInfoInDatabase(fileUrl, kmlInfo, newFileUrl);

            return newFileUrl;
        } catch (Exception e) {
            log.error("更新KMZ文件失败: {}", e.getMessage(), e);
            throw new RuntimeException("更新KMZ文件失败", e);
        }
    }

    private KmlParams buildKmlParams(KmlInfo kmlInfo) {
        KmlParams kmlParams = new KmlParams();
        KmlFolder folder = kmlInfo.getDocument().getFolder();
        kmlParams.setGlobalHeight(Double.valueOf(kmlInfo.getDocument().getKmlMissionConfig().getGlobalRTHHeight()));
        kmlParams.setAutoFlightSpeed(Double.valueOf(folder.getAutoFlightSpeed()));

        WaypointHeadingReq waypointHeadingReq = new WaypointHeadingReq();
        waypointHeadingReq.setWaypointHeadingMode(folder.getGlobalWaypointHeadingParam().getWaypointHeadingMode());
        waypointHeadingReq.setWaypointHeadingAngle(StringUtils.isNotBlank(folder.getGlobalWaypointHeadingParam().getWaypointHeadingAngle()) ?
                Double.valueOf(folder.getGlobalWaypointHeadingParam().getWaypointHeadingAngle()) : null);
        waypointHeadingReq.setWaypointPoiPoint(StringUtils.isNotBlank(folder.getGlobalWaypointHeadingParam().getWaypointPoiPoint()) ? folder.getGlobalWaypointHeadingParam().getWaypointPoiPoint() : null);
        kmlParams.setWaypointHeadingReq(waypointHeadingReq);

        WaypointTurnReq waypointTurnReq = new WaypointTurnReq();
        waypointTurnReq.setWaypointTurnMode(folder.getGlobalWaypointTurnMode());
        waypointTurnReq.setUseStraightLine(StringUtils.isNotBlank(folder.getGlobalUseStraightLine()) ? Integer.valueOf(folder.getGlobalUseStraightLine()) : null);

        kmlParams.setWaypointTurnReq(waypointTurnReq);
        kmlParams.setGimbalPitchMode(folder.getGimbalPitchMode());
        kmlParams.setPayloadPosition(Integer.valueOf(kmlInfo.getDocument().getKmlMissionConfig().getPayloadInfo().getPayloadPositionIndex()));
        kmlParams.setImageFormat(folder.getPayloadParam().getImageFormat());
        return kmlParams;
    }

    private void handleRouteUpdate(KmlParams kmlParams) {
        // 航线更新处理逻辑
        // 这里可以根据需要添加具体的更新逻辑
        log.info("处理航线更新，模板类型: {}", kmlParams.getTemplateType());
    }

    @Override
    public void buildKmz(UavRouteReq uavRouteReq) {
        KmlParams kmlParams = new KmlParams();
        BeanUtils.copyProperties(uavRouteReq, kmlParams);
        kmlParams.setRoutePointList(BeanUtil.copyToList(uavRouteReq.getRoutePointList(), RoutePointInfo.class));
        String kmzUrl = RouteFileUtils.buildKmz("航线kmz文件", kmlParams);
        log.info("生成KMZ文件成功: {}", kmzUrl);
    }

    /**
     * 根据KmlParams构建KMZ文件
     *
     * @param kmlParams KML参数
     * @return KMZ文件URL
     */
    public String buildKmz(KmlParams kmlParams) {
        return RouteFileUtils.buildKmz("航线kmz文件", kmlParams);
    }

    /**
     * 根据航线ID从数据库构建KMZ文件
     *
     * @param routeId 航线ID
     * @return KMZ文件URL
     */
    public String buildKmzFromDatabase(Long routeId) {
        try {
            // 1. 查询航线基本信息
            UavRoute uavRoute = uavRouteMapper.selectById(routeId);
            if (uavRoute == null) {
                throw new RuntimeException("航线不存在，ID: " + routeId);
            }

            log.info("开始从数据库构建KMZ文件，航线ID: {}, 航线名称: {}", routeId, uavRoute.getRouteName());

            // 2. 查询航点信息
            QueryWrapper<RoutePoint> pointQuery = new QueryWrapper<>();
            pointQuery.eq("route_id", routeId).orderByAsc("point_index");
            List<RoutePoint> routePoints = routePointMapper.selectList(pointQuery);
            if (CollectionUtil.isEmpty(routePoints)) {
                log.warn("航线没有航点数据，航线ID: {}", routeId);
            }

            // 3. 构建KmlParams
            KmlParams kmlParams = new KmlParams();
            kmlParams.setTemplateType(uavRoute.getTemplateType());
            kmlParams.setDroneType(uavRoute.getDroneType() != null ? Integer.valueOf(uavRoute.getDroneType()) : null);
            kmlParams.setPayloadType(uavRoute.getPayloadType() != null ? Integer.valueOf(uavRoute.getPayloadType()) : null);
            kmlParams.setGlobalHeight(uavRoute.getGlobalHeight() != null ? uavRoute.getGlobalHeight().doubleValue() : null);
            kmlParams.setAutoFlightSpeed(uavRoute.getAutoFlightSpeed() != null ? uavRoute.getAutoFlightSpeed().doubleValue() : null);
            kmlParams.setFinishAction(uavRoute.getFinishAction());
            kmlParams.setExitOnRcLostAction(uavRoute.getExitOnRcLostAction());

            // 4. 转换航点数据
            List<RoutePointInfo> routePointInfoList = new ArrayList<>();
            for (RoutePoint routePoint : routePoints) {
                RoutePointInfo routePointInfo = new RoutePointInfo();
                routePointInfo.setRoutePointIndex(routePoint.getPointIndex());
                routePointInfo.setLongitude(routePoint.getLongitude() != null ? routePoint.getLongitude().doubleValue() : null);
                routePointInfo.setLatitude(routePoint.getLatitude() != null ? routePoint.getLatitude().doubleValue() : null);
                routePointInfo.setHeight(routePoint.getHeight() != null ? routePoint.getHeight().doubleValue() : null);
                routePointInfo.setSpeed(routePoint.getSpeed() != null ? routePoint.getSpeed().doubleValue() : null);
                // RoutePointInfo类中没有useGlobalSpeed、headingAngle、turnMode字段
                // 使用实际存在的字段进行设置
                if (routePoint.getWaypointHeadingAngle() != null) {
                    WaypointHeadingReq headingReq = new WaypointHeadingReq();
                    // 根据实际需要设置航向参数
                    routePointInfo.setWaypointHeadingReq(headingReq);
                }
                if (routePoint.getWaypointTurnMode() != null) {
                    WaypointTurnReq turnReq = new WaypointTurnReq();
                    // 根据实际需要设置转弯参数
                    routePointInfo.setWaypointTurnReq(turnReq);
                }

                routePointInfoList.add(routePointInfo);
            }

            kmlParams.setRoutePointList(routePointInfoList);

            // 5. 构建KMZ文件
            String kmzFileUrl = RouteFileUtils.buildKmz(uavRoute.getRouteName(), kmlParams);

            log.info("从数据库构建KMZ文件成功，航线ID: {}, 文件URL: {}", routeId, kmzFileUrl);
            return kmzFileUrl;

        } catch (Exception e) {
            log.error("从数据库构建KMZ文件失败，航线ID: {}", routeId, e);
            throw new RuntimeException("构建KMZ文件失败", e);
        }
    }

    /**
     * 根据航线名称从数据库构建KMZ文件
     *
     * @param routeName 航线名称
     * @return KMZ文件URL
     */
    public String buildKmzFromDatabaseByName(String routeName) {
        try {
            // 根据航线名称查询航线
            QueryWrapper<UavRoute> routeQuery = new QueryWrapper<>();
            routeQuery.eq("route_name", routeName);
            UavRoute uavRoute = uavRouteMapper.selectOne(routeQuery);

            if (uavRoute == null) {
                throw new RuntimeException("航线不存在，名称: " + routeName);
            }

            return buildKmzFromDatabase(uavRoute.getId());

        } catch (Exception e) {
            log.error("根据航线名称从数据库构建KMZ文件失败，航线名称: {}", routeName, e);
            throw new RuntimeException("构建KMZ文件失败", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public KmlInfo parseKmz(String fileUrl) throws IOException {
        File file = FileUtils.downloadUrlToTempFile(fileUrl);
        try (ArchiveInputStream archiveInputStream = new ZipArchiveInputStream(FileUtil.getInputStream(file))) {
            ArchiveEntry entry;
            KmlInfo kmlInfo = new KmlInfo();
            KmlInfo wpmlInfo = new KmlInfo();
            while (!Objects.isNull(entry = archiveInputStream.getNextEntry())) {
                String name = entry.getName();
                if (name.toLowerCase().endsWith(".kml")) {
                    kmlInfo = RouteFileUtils.parseKml(archiveInputStream);
                } else if (name.toLowerCase().endsWith(".wpml")) {
                    wpmlInfo = RouteFileUtils.parseKml(archiveInputStream);
                }
            }

            // 保存解析的航线数据到数据库
            if (kmlInfo != null && kmlInfo.getDocument() != null) {
                saveKmlInfoToDatabase(kmlInfo, fileUrl);
                log.info("成功解析并保存KMZ文件到数据库: {}", fileUrl);
            }

            return kmlInfo;
        } catch (Exception e) {
            log.error("解析KMZ文件失败: {}", fileUrl, e);
            throw new RuntimeException("解析KMZ文件失败", e);
        }
    }

    /**
     * 将解析的KML信息保存到数据库
     *
     * @param kmlInfo KML信息
     * @param fileUrl 文件URL
     */
    private void saveKmlInfoToDatabase(KmlInfo kmlInfo, String fileUrl) {
        try {
            KmlDocument document = kmlInfo.getDocument();
            KmlFolder folder = document.getFolder();

            // 1. 保存航线基本信息
            UavRoute uavRoute = new UavRoute();
            uavRoute.setRouteName("解析航线_" + System.currentTimeMillis()); // KmlFolder没有name字段，使用默认名称
            uavRoute.setTemplateType("waypoint"); // 默认航点模式
            uavRoute.setDroneType("DJI"); // 默认大疆无人机
            uavRoute.setPayloadType("camera"); // 默认相机负载
            // UavRoute实体类中没有fileUrl字段，移除此设置
            // uavRoute.setFileUrl(fileUrl);
            uavRoute.setGlobalHeight(document.getKmlMissionConfig() != null &&
                    StringUtils.isNotBlank(document.getKmlMissionConfig().getGlobalRTHHeight()) ?
                    new BigDecimal(document.getKmlMissionConfig().getGlobalRTHHeight()) : BigDecimal.ZERO);
            uavRoute.setAutoFlightSpeed(StringUtils.isNotBlank(folder.getAutoFlightSpeed()) ?
                    new BigDecimal(folder.getAutoFlightSpeed()) : new BigDecimal("5.0"));
            uavRoute.setFinishAction(document.getKmlMissionConfig() != null ?
                    document.getKmlMissionConfig().getFinishAction() : "goHome");
            uavRoute.setExitOnRcLostAction(document.getKmlMissionConfig() != null ?
                    document.getKmlMissionConfig().getExecuteRCLostAction() : "goHome");
            uavRoute.setCreatedAt(LocalDateTime.now());

            uavRouteMapper.insert(uavRoute);
            Long routeId = uavRoute.getId();
            log.info("保存航线信息成功，航线ID: {}", routeId);

            // 2. 保存航点信息
            if (CollectionUtil.isNotEmpty(folder.getPlacemarkList())) {
                List<RoutePoint> routePoints = new ArrayList<>();
                List<PointAction> pointActions = new ArrayList<>();

                for (int i = 0; i < folder.getPlacemarkList().size(); i++) {
                    KmlPlacemark placemark = folder.getPlacemarkList().get(i);

                    // 保存航点
                    RoutePoint routePoint = new RoutePoint();
                    routePoint.setRouteId(routeId);
                    routePoint.setPointIndex(i + 1);

                    if (placemark.getKmlPoint() != null && StringUtils.isNotBlank(placemark.getKmlPoint().getCoordinates())) {
                        String[] coords = placemark.getKmlPoint().getCoordinates().split(",");
                        if (coords.length >= 3) {
                            routePoint.setLongitude(new BigDecimal(coords[0]));
                            routePoint.setLatitude(new BigDecimal(coords[1]));
                            routePoint.setHeight(new BigDecimal(coords[2]));
                        }
                    }

                    // 从KmlPlacemark直接获取航点参数
                    if (StringUtils.isNotBlank(placemark.getWaypointSpeed())) {
                        routePoint.setSpeed(new BigDecimal(placemark.getWaypointSpeed()));
                    }

                    // 获取航向角参数
                    if (placemark.getWaypointHeadingParam() != null &&
                            StringUtils.isNotBlank(placemark.getWaypointHeadingParam().getWaypointHeadingAngle())) {
                        routePoint.setWaypointHeadingAngle(new BigDecimal(placemark.getWaypointHeadingParam().getWaypointHeadingAngle()));
                    }

                    // 获取转弯模式参数
                    if (placemark.getWaypointTurnParam() != null &&
                            StringUtils.isNotBlank(placemark.getWaypointTurnParam().getWaypointTurnMode())) {
                        routePoint.setTurnMode(placemark.getWaypointTurnParam().getWaypointTurnMode());
                    }

                    routePoint.setCreatedAt(LocalDateTime.now());
                    routePoints.add(routePoint);
                }

                // 批量保存航点
                for (RoutePoint routePoint : routePoints) {
                    routePointMapper.insert(routePoint);
                }

                log.info("保存航点信息成功，共{}个航点", routePoints.size());
            }

        } catch (Exception e) {
            log.error("保存KML信息到数据库失败", e);
            throw new RuntimeException("保存数据库失败", e);
        }
    }

    /**
     * 更新数据库中的KML信息
     *
     * @param oldFileUrl 原文件URL
     * @param kmlInfo    新的KML信息
     * @param newFileUrl 新文件URL
     */
    private void updateKmlInfoInDatabase(String oldFileUrl, KmlInfo kmlInfo, String newFileUrl) {
        try {
            // 1. 根据原文件URL查找航线
            QueryWrapper<UavRoute> routeQuery = new QueryWrapper<>();
            routeQuery.eq("file_url", oldFileUrl);
            UavRoute existingRoute = uavRouteMapper.selectOne(routeQuery);

            if (existingRoute == null) {
                log.warn("未找到对应的航线记录，文件URL: {}", oldFileUrl);
                // 如果找不到现有记录，则作为新记录保存
                saveKmlInfoToDatabase(kmlInfo, newFileUrl);
                return;
            }

            Long routeId = existingRoute.getId();
            log.info("找到现有航线记录，航线ID: {}", routeId);

            // 2. 更新航线基本信息
            KmlDocument document = kmlInfo.getDocument();
            KmlFolder folder = document.getFolder();

            // KmlFolder没有name字段，保持原有名称
            // existingRoute.setRouteName(folder.getName() != null ? folder.getName() : existingRoute.getRouteName());
            // UavRoute实体类中没有fileUrl字段，移除此设置
            // existingRoute.setFileUrl(newFileUrl);
            existingRoute.setGlobalHeight(document.getKmlMissionConfig() != null &&
                    StringUtils.isNotBlank(document.getKmlMissionConfig().getGlobalRTHHeight()) ?
                    new BigDecimal(document.getKmlMissionConfig().getGlobalRTHHeight()) : existingRoute.getGlobalHeight());
            existingRoute.setAutoFlightSpeed(StringUtils.isNotBlank(folder.getAutoFlightSpeed()) ?
                    new BigDecimal(folder.getAutoFlightSpeed()) : existingRoute.getAutoFlightSpeed());
            existingRoute.setFinishAction(document.getKmlMissionConfig() != null ?
                    document.getKmlMissionConfig().getFinishAction() : existingRoute.getFinishAction());
            existingRoute.setExitOnRcLostAction(document.getKmlMissionConfig() != null ?
                    document.getKmlMissionConfig().getExecuteRCLostAction() : existingRoute.getExitOnRcLostAction());
            existingRoute.setUpdatedAt(LocalDateTime.now());

            uavRouteMapper.updateById(existingRoute);
            log.info("更新航线基本信息成功，航线ID: {}", routeId);

            // 3. 删除原有航点和动作数据
            routePointMapper.deleteByRouteId(routeId);
            pointActionMapper.deleteByRouteId(routeId);
            routeActionMapper.deleteByRouteId(routeId);
            mappingConfigMapper.deleteByRouteId(routeId);
            surveyAreaCoordinateMapper.deleteByRouteId(routeId);
            log.info("删除原有航点和动作数据成功");

            // 4. 重新保存航点信息
            if (CollectionUtil.isNotEmpty(folder.getPlacemarkList())) {
                List<RoutePoint> routePoints = new ArrayList<>();

                for (int i = 0; i < folder.getPlacemarkList().size(); i++) {
                    KmlPlacemark placemark = folder.getPlacemarkList().get(i);

                    // 保存航点
                    RoutePoint routePoint = new RoutePoint();
                    routePoint.setRouteId(routeId);
                    routePoint.setPointIndex(i + 1);

                    if (placemark.getKmlPoint() != null && StringUtils.isNotBlank(placemark.getKmlPoint().getCoordinates())) {
                        String[] coords = placemark.getKmlPoint().getCoordinates().split(",");
                        if (coords.length >= 3) {
                            routePoint.setLongitude(new BigDecimal(coords[0]));
                            routePoint.setLatitude(new BigDecimal(coords[1]));
                            routePoint.setHeight(new BigDecimal(coords[2]));
                        }
                    }

                    // 从KmlPlacemark直接获取航点参数
                    if (StringUtils.isNotBlank(placemark.getWaypointSpeed())) {
                        routePoint.setSpeed(new BigDecimal(placemark.getWaypointSpeed()));
                    }

                    // 获取航向角参数
                    if (placemark.getWaypointHeadingParam() != null &&
                            StringUtils.isNotBlank(placemark.getWaypointHeadingParam().getWaypointHeadingAngle())) {
                        routePoint.setWaypointHeadingAngle(new BigDecimal(placemark.getWaypointHeadingParam().getWaypointHeadingAngle()));
                    }

                    // 获取转弯模式参数
                    if (placemark.getWaypointTurnParam() != null &&
                            StringUtils.isNotBlank(placemark.getWaypointTurnParam().getWaypointTurnMode())) {
                        routePoint.setTurnMode(placemark.getWaypointTurnParam().getWaypointTurnMode());
                    }

                    routePoint.setCreatedAt(LocalDateTime.now());
                    routePoints.add(routePoint);
                }

                // 批量保存航点
                for (RoutePoint routePoint : routePoints) {
                    routePointMapper.insert(routePoint);
                }

                log.info("更新航点信息成功，共{}个航点", routePoints.size());
            }

        } catch (Exception e) {
            log.error("更新数据库中的KML信息失败", e);
            throw new RuntimeException("更新数据库失败", e);
        }
    }
}
