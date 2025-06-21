package com.cleaner.djuav.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:Cleaner
 * Date: 2024/12/22 10:46
 **/
@Data
public class MappingTypeReq implements Serializable {

    /**
     * 采集方式
     */
    private String collectionMethod;

    /**
     * 镜头类型
     */
    private String lensType;

    /**
     * 航向重叠率
     */
    private Integer overlapH;

    /**
     * 旁向重叠率
     */
    private Integer overlapW;

    /**
     * 是否开启高程优化
     */
    private Integer elevationOptimizeEnable;

    /**
     * 拍照模式
     */
    private String shootType;

    /**
     * 航线方向
     */
    private String direction;

    /**
     * 测区外扩距离
     */
    private String margin;

    /**
     * 测区多边形坐标  经度,纬度,高度
     */
    private List<CoordinatePointReq> coordinates;


}
