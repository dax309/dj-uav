template.kml 说明
===============

2025-02-25

2.3 分

16 用户已评分

[Github Edit open in new window](https://github.com/dji-sdk/Cloud-API-Doc/blob/master/docs/cn/60.api-reference/00.dji-wpml/20.template-kml.md)

*   [文件介绍](/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/template-kml.html#文件介绍)
*   [元素说明](/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/template-kml.html#元素说明)

[#](#文件介绍) 文件介绍
---------------

`template.kml`是模板文件，可以被 DJI Pilot 2、DJI Flighthub 2 或者其它软件解析，生成最终提供给无人机执行的路径和动作，即`waylines.wpml`文件。`template.kml`文件由三部分组成：

1.  创建信息：主要包含航线文件本身的信息，例如文件的创建、更新时间等。
2.  任务信息：主要包含 `wpml:missionConfig`元素，定义航线任务的全局参数等。
3.  模板信息：主要包含`Folder`元素，定义航线的模板信息（如航点飞行、建图航拍、倾斜摄影、航带飞行等）。不同航线模板类型包含的元素不同。

### [#](#示例文件) 示例文件

`template.kml`示例文件如下（以航点飞行模板为例）：

```
<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:wpml="http://www.dji.com/wpmz/1.0.2">
<Document>

  <!-- Step 1: Implement File Creation Information -->
  <wpml:author>Name</wpml:author>
  <wpml:createTime>1637600807044</wpml:createTime>
  <wpml:updateTime>1637600875837</wpml:updateTime>
 
  <!-- Step 2: Setup Mission Configuration -->
  <wpml:missionConfig>
    <wpml:flyToWaylineMode>safely</wpml:flyToWaylineMode>
    <wpml:finishAction>goHome</wpml:finishAction>
    <wpml:exitOnRCLost>goContinue</wpml:exitOnRCLost>
    <wpml:executeRCLostAction>hover</wpml:executeRCLostAction>
    <wpml:takeOffSecurityHeight>20</wpml:takeOffSecurityHeight>
    <wpml:takeOffRefPoint>23.98057,115.987663,100</wpml:takeOffRefPoint>
    <wpml:takeOffRefPointAGLHeight>35</wpml:takeOffRefPointAGLHeight>
    <wpml:globalTransitionalSpeed>8</wpml:globalTransitionalSpeed>
    <wpml:droneInfo>
      <!-- Declare drone model with M30 -->
      <wpml:droneEnumValue>67</wpml:droneEnumValue>
      <wpml:droneSubEnumValue>0</wpml:droneSubEnumValue>
    </wpml:droneInfo>
    <wpml:payloadInfo>
      <!-- Declare payload model with M30 -->
      <wpml:payloadEnumValue>52</wpml:payloadEnumValue>
      <wpml:payloadPositionIndex>0</wpml:payloadPositionIndex>
    </wpml:payloadInfo>
  </wpml:missionConfig>
 
  <!-- Step 3: Setup A Folder for Waypoint Template -->
  <Folder>
    <wpml:templateType>waypoint</wpml:templateType>
    <wpml:templateId>0</wpml:templateId>
    <wpml:waylineCoordinateSysParam>
      <wpml:coordinateMode>WGS84</wpml:coordinateMode>
      <wpml:heightMode>EGM96</wpml:heightMode>
      <wpml:globalShootHeight>50</wpml:globalShootHeight>
      <wpml:positioningType>GPS</wpml:positioningType>
      <wpml:surfaceFollowModeEnable>1</wpml:surfaceFollowModeEnable>
      <wpml:surfaceRelativeHeight>100</wpml:surfaceRelativeHeight>
    </wpml:waylineCoordinateSysParam>
    <wpml:autoFlightSpeed>7</wpml:autoFlightSpeed>
    <wpml:gimbalPitchMode>usePointSetting</wpml:gimbalPitchMode>
    <wpml:globalWaypointHeadingParam>
      <wpml:waypointHeadingMode>followWayline</wpml:waypointHeadingMode>
      <wpml:waypointHeadingAngle>45</wpml:waypointHeadingAngle>
      <wpml:waypointPoiPoint>24.323345,116.324532,31.000000</wpml:waypointPoiPoint>
      <wpml:waypointHeadingPathMode>clockwise</wpml:waypointHeadingPathMode>
    </wpml:globalWaypointHeadingParam>
    <wpml:globalWaypointTurnMode>toPointAndStopWithDiscontinuityCurvature</wpml:globalWaypointTurnMode>
    <wpml:globalUseStraightLine>0</wpml:globalUseStraightLine>
    <Placemark>
      <Point>
        <!-- Fill longitude and latitude here -->
        <coordinates>
          longitude,latitude
        </coordinates>
      </Point>
      <wpml:index>0</wpml:index>
      <wpml:ellipsoidHeight>90.2</wpml:ellipsoidHeight>
      <wpml:height>100</wpml:height>
      <wpml:useGlobalHeight>1</wpml:useGlobalHeight>
      <wpml:useGlobalSpeed>1</wpml:useGlobalSpeed>
      <wpml:useGlobalHeadingParam>1</wpml:useGlobalHeadingParam>
      <wpml:useGlobalTurnParam>1</wpml:useGlobalTurnParam>
      <wpml:gimbalPitchAngle>0</wpml:gimbalPitchAngle>
    </Placemark>
    <Placemark>
      <Point>
        <!-- Fill longitude and latitude here -->
        <coordinates>
          longitude,latitude
        </coordinates>
      </Point>
      <wpml:index>1</wpml:index>
      <wpml:ellipsoidHeight>90.2</wpml:ellipsoidHeight>
      <wpml:height>100</wpml:height>
      <wpml:useGlobalHeight>1</wpml:useGlobalHeight>
      <wpml:useGlobalSpeed>1</wpml:useGlobalSpeed>
      <wpml:useGlobalHeadingParam>1</wpml:useGlobalHeadingParam>
      <wpml:useGlobalTurnParam>1</wpml:useGlobalTurnParam>
      <wpml:gimbalPitchAngle>0</wpml:gimbalPitchAngle>
      <!-- Declare action group for waypoint 1# -->
      <wpml:actionGroup>
        <wpml:actionGroupId>0</wpml:actionGroupId>
        <wpml:actionGroupStartIndex>1</wpml:actionGroupStartIndex>
        <wpml:actionGroupEndIndex>1</wpml:actionGroupEndIndex>
        <wpml:actionGroupMode>sequence</wpml:actionGroupMode>
        <wpml:actionTrigger>
          <wpml:actionTriggerType>reachPoint</wpml:actionTriggerType>
        </wpml:actionTrigger>
        <!-- Declare the 1st action: rotate gimbal -->
        <wpml:action>
          <wpml:actionId>0</wpml:actionId>
          <wpml:actionActuatorFunc>gimbalRotate</wpml:actionActuatorFunc>
          <wpml:actionActuatorFuncParam>
            <wpml:gimbalRotateMode>absoluteAngle</wpml:gimbalRotateMode>
            <wpml:gimbalPitchRotateEnable>0</wpml:gimbalPitchRotateEnable>
            <wpml:gimbalPitchRotateAngle>0</wpml:gimbalPitchRotateAngle>
            <wpml:gimbalRollRotateEnable>0</wpml:gimbalRollRotateEnable>
            <wpml:gimbalRollRotateAngle>0</wpml:gimbalRollRotateAngle>
            <wpml:gimbalYawRotateEnable>1</wpml:gimbalYawRotateEnable>
            <wpml:gimbalYawRotateAngle>30</wpml:gimbalYawRotateAngle>
            <wpml:gimbalRotateTimeEnable>0</wpml:gimbalRotateTimeEnable>
            <wpml:gimbalRotateTime>0</wpml:gimbalRotateTime>
            <wpml:payloadPositionIndex>0</wpml:payloadPositionIndex>
          </wpml:actionActuatorFuncParam>
        </wpml:action>
        <!-- Declare the 2nd action: take photo -->
        <wpml:action>
          <wpml:actionId>1</wpml:actionId>
          <wpml:actionActuatorFunc>takePhoto</wpml:actionActuatorFunc>
          <wpml:actionActuatorFuncParam>
            <wpml:fileSuffix>point1</wpml:fileSuffix>
            <wpml:payloadPositionIndex>0</wpml:payloadPositionIndex>
          </wpml:actionActuatorFuncParam>
        </wpml:action>
      </wpml:actionGroup>
    </Placemark>
  </Folder>
</Document>
</kml>

```

[#](#元素说明) 元素说明
---------------

### [#](#创建信息-父元素-document) 创建信息（父元素：`<Document>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:author | 文件创建作者 | 字符串 | \- | \- | 无，非必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:createTime | 文件创建时间（Unix Timestamp） | 整型 | ms | \- | 无，非必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:updateTime | 文件更新时间（Unix Timestamp） | 整型 | ms | \- | 无，非必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

### [#](#任务信息-父元素-wpml-missionconfig) 任务信息（父元素：`<wpml:missionConfig>`）

| 元素 | 名称 | 类型 | 单位
 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:flyToWaylineMode | 飞向首航点模式 | 枚举-string | \- | safely：安全模式  
（M300）飞行器起飞，上升至首航点高度，再平飞至首航点。如果首航点低于起飞点，则起飞后平飞至首航点上方再下降。  
（M30）飞行器起飞，上升至首航点高度，再平飞至首航点。如果首航点低于“安全起飞高度”，则起飞至“安全起飞高度”后，平飞至首航点上方再下降。注意“安全起飞高度”仅在飞行器未起飞时生效。  
  
pointToPoint：倾斜飞行模式  
（M300）飞行器起飞后，倾斜飞到首航点。  
（M30）飞行器起飞至“安全起飞高度”，再倾斜爬升至首航点。如果首航点高度低于“安全起飞高度”，则先平飞后下降。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:finishAction | 航线结束动作 | 枚举-string | \- | goHome：飞行器完成航线任务后，退出航线模式并返航。noAction：飞行器完成航线任务后，退出航线模式。  
autoLand：飞行器完成航线任务后，退出航线模式并原地降落。  
gotoFirstWaypoint：飞行器完成航线任务后，立即飞向航线起始点，到达后退出航线模式。\* 注：以上动作执行过程，若飞行器退出了航线模式且进入失控状态，则会优先执行失控动作。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:exitOnRCLost | 失控是否继续执行航线 | 枚举-string | \- | goContinue：继续执行航线  
executeLostAction：退出航线，执行失控动作 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:executeRCLostAction | 失控动作类型 | 枚举-string | \- | goBack：返航。飞行器从失控位置飞向起飞点  
landing：降落。飞行器从失控位置原地降落  
hover：悬停。飞行器从失控位置悬停 | 无，当wpml:exitOnRCLost为executeLostAction时为必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:takeOffSecurityHeight | 安全起飞高度 | 浮点型 | m | 遥控器场景 \[1.2,1500\]，机场场景 \[8,1500\] （高度模式：相对起飞点高度）  
\* 注：飞行器起飞后，先爬升至该高度，再根据“飞向首航点模式”的设置飞至首航点。该元素仅在飞行器未起飞时生效。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:globalTransitionalSpeed | 全局航线过渡速度 | 浮点型 | m/s | \[1,15\]  
\* 注：飞行器飞往每条航线首航点的速度。航线任务中断时，飞行器从当前位置恢复至断点的速度。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:globalRTHHeight | 全局返航高度 | 浮点型 | m | \[2,1500\] \*注：飞行器返航时，先爬升至该高度，再进行返航 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:takeOffRefPoint<x,y,z> | 参考起飞点 | 浮点型 | °, °, m | \[-90,90\],\[-180,180\],无限制  
\* 注：<x,y,z>指<纬度，经度，高度>。“参考起飞点”仅做航线规划参考，飞行器执行航线时以飞行器真实的起飞点为准，高度使用椭球高。 | 无，非必需元素 | M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:takeOffRefPointAGLHeight | 参考起飞点海拔高度 | 浮点型 | m | \* 注：”参考起飞点“海拔高度，与“参考起飞点”中的椭球高度对应。 | 无，非必需元素 | M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:droneInfo | 飞行器机型信息 | \- | \- | _注：请在[共用元素信息open in new window](https://developer.dji.com/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/common-element.html)章节阅读详细信息_ | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:payloadInfo | 负载机型信息 | \- | \- | _注：请在[共用元素信息open in new window](https://developer.dji.com/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/common-element.html)章节阅读详细信息_ | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:autoRerouteInfo | 航线绕行 | \- | \- | \- | \- | M3D/M3TD，M4E/M4T |

### [#](#模板信息-父元素-folder) 模板信息（父元素：`<Folder>`）

#### [#](#模板共用的元素-父元素-folder) 模板共用的元素（父元素：`<Folder>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:templateType | 预定义模板类型  
\* 注：模板为用户提供了快速生成航线的方案。用户填充模板元素，再导入大疆支持客户端（如DJI Pilot），即可快速生成可执行的测绘/巡检航线。 | 枚举-string | \- | waypoint：航点飞行  
mapping2d：建图航拍  
mapping3d：倾斜摄影  
mappingStrip：航带飞行 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:templateId | 模板ID  
\* 注：在一个kmz文件内该ID唯一。建议从0开始单调连续递增。在template.kml和waylines.wpml文件中，将使用该id将模板与所生成的可执行航线进行关联。 | 整型 | \- | \[0, 65535\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:autoFlightSpeed | 全局航线飞行速度 | 浮点型 | m/s | \[1,15\]  
\* 注：该元素定义了此模板生成的整段航线中，飞行器的目标飞行速度。如果额外定义了某航点的该元素，则局部定义会覆盖全局定义。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:waylineCoordinateSysParam | 坐标系参数 | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:payloadParam | 负载设置 | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

### [#](#wpml-payloadparam) `<wpml:payloadParam>`

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:payloadPositionIndex | 负载挂载位置 | 整型 | \- | 负载挂载位置枚举值请参考[产品支持open in new window](https://developer.dji.com/doc/cloud-api-tutorial/cn/overview/product-support.html)页面中的`相机枚举值`中`type-subtype-gimbalindex`中的`gimbalindex`字段 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:focusMode | 负载对焦模式 | 枚举-string | \- | firstPoint：首个航点自动对焦  
custom：标定对焦值对焦 | \- | M300 RTK，M350 RTK |
| wpml:meteringMode | 负载测光模式 | 枚举-string | \- | average：全局测光  
spot：点测光 | \- | M300 RTK，M350 RTK |
| wpml:dewarpingEnable | 是否开启畸变矫正 | 布尔型 | \- | 0：不开启  
1：开启 | \- | M300 RTK，M350 RTK |
| wpml:returnMode | 激光雷达回波模式 | 枚举-string | \- | singleReturnStrongest：单回波  
dualReturn：双回波  
tripleReturn：三回波 | \- | M300 RTK，M350 RTK |
| wpml:samplingRate | 负载采样率 | 整型 | Hz | 60000,  
80000,  
120000,  
160000,  
180000,  
240000 | \- | M300 RTK，M350 RTK |
| wpml:scanningMode | 负载扫描模式 | 枚举-string | \- | repetitive：重复扫描  
nonRepetitive：非重复扫描 | \- | M300 RTK，M350 RTK |
| wpml:modelColoringEnable | 真彩上色 | 布尔型 |  | 0: 不上色  
1: 真彩上色 | \- | M300 RTK，M350 RTK |
| wpml:imageFormat | 图片格式列表 | 枚举-string（列表） |  | wide：存储广角镜头照片  
zoom：存储变焦镜头照片  
ir：存储红外镜头照片  
narrow\_band: 存储窄带镜头拍摄照片  
visable：可见光照片  
\* 注：存储多个镜头照片，格式如“`<wpml:imageFormat>wide,ir</wpml:imageFormat>`” | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

#### [#](#航点飞行模板元素-父元素-folder) 航点飞行模板元素（父元素：`<Folder>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:globalWaypointTurnMode | 全局航点类型（全局航点转弯模式） | 枚举-string | \- | coordinateTurn：协调转弯，不过点，提前转弯  
toPointAndStopWithDiscontinuityCurvature：直线飞行，飞行器到点停  
toPointAndStopWithContinuityCurvature：曲线飞行，飞行器到点停  
toPointAndPassWithContinuityCurvature：曲线飞行，飞行器过点不停 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:globalUseStraightLine | 全局航段轨迹是否尽量贴合直线 | 布尔型 | \- | 0：航段轨迹全程为曲线  
1：航段轨迹尽量贴合两点连线 | 必需元素  
\* 注：当且仅当“wpml:globalWaypointTurnMode”被设置为“toPointAndStopWithContinuityCurvature”或“toPointAndPassWithContinuityCurvature”时必需。如果额外定义了某航点的该元素，则局部定义会覆盖全局定义。 | M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:gimbalPitchMode | 云台俯仰角控制模式 | 枚举-string | \- | manual：手动控制。飞行器从一个航点飞向下一个航点的过程中，支持用户手动控制云台的俯仰角度。若无用户控制，则保持飞离航点时的云台俯仰角度。  
usePointSetting：依照每个航点设置。飞行器从一个航点飞向下一个航点的过程中，云台俯仰角均匀过渡至下一个航点的俯仰角。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:globalHeight | 全局航线高度（相对起飞点高度）  
 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:globalWaypointHeadingParam | 全局偏航角模式参数 | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| Placemark(Point) | 航点信息（包括航点经纬度和高度等） | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

#### [#](#建图航拍模板元素-父元素-placemark) 建图航拍模板元素（父元素：`<Placemark>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:caliFlightEnable | 是否开启标定飞行  
\* 注：仅适用于M300 RTK与M350 RTK机型 | 布尔型 | \- | 0：不开启  
1：开启，航线中自动进行惯导标定，保证模型精度。航线收尾会进行三次加减速飞行，航线拐弯处自动外扩进行加减速飞行。航线过长会均匀插入加减速飞行，每次标定后飞行时间不会超过100s。 | \- | M300 RTK，M350 RTK |
| wpml:elevationOptimizeEnable | 是否开启高程优化 | 布尔型 | \- | 0：不开启  
1：开启，飞行器会在航线执行完毕后，飞向测区中心采集一组倾斜照片，优化高程精度。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:smartObliqueEnable | 是否开启智能摆拍  
\* 注：M300 RTK 与 M350 RTK 机型仅支持 P1 相机 | 布尔型 | \- | 0：不开启  
1：开启，飞行器在单次建图航拍任务过程中，可通过云台摆动完成正射与倾斜照片拍摄。 | \- | M300 RTK，M350 RTK，M3E/M3T/M3M，M3D |
| wpml:smartObliqueGimbalPitch | 智能摆拍拍摄俯仰角  
\* 注：M300 RTK 与 M350 RTK 机型仅支持 P1 相机 | 整型 | ° | 对应机型云台可转动范围 | \- | M300 RTK，M350 RTK，M3E/M3T/M3M，M3D |
| wpml:shootType | 拍照模式（定时或定距） | 枚举-string | \- | time：等时间拍照  
distance：等间隔拍照  
\* 注：建议使用“time”等时间拍照。在template.kml文件中定义“拍照模式”、“重叠率”和“飞行速度”，计算后得出间隔时间或间隔距离距离写入waylines.wpml中。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:direction | 航线方向 | 整型 | \- | \[0, 360\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:margin | 测区外扩距离 | 整型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:overlap | 重叠率参数 | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:ellipsoidHeight | 全局航线高度（椭球高）  
\* 注：如果 wpml:height 选用相对起飞点高度，则 wpml:ellipsoidHeight 和 wpml:height 相同；如果 wpml:height 选用 EGM96 海拔高度或 AGL 相对地面高度，则 wpml:ellipsoidHeight 由 wpml:height 做相应转换得到。 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:height | 全局航线高度（EGM96海拔高/相对起飞点高度/AGL相对地面高度）  
\* 注：该元素与 wpml:ellipsoidHeight 配合使用，二者是同一位置不同高程参考平面的表达。 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:facadeWaylineEnable | 是否开启斜立面  
\* 注：该元素与 ”LinearRing“ 配合使用，开启后将按照椭球该读取其中的高度值 | 布尔型 | \- | 0：不开启  
1：开启 | \- | M3E/M3T/M3M |
| Polygon | 测区多边形  
\* 注：此处格式如“`<Polygon> <outerBoundaryIs> <LinearRing> <coordinates> 经度,纬度,高度 经度,纬度,高度 经度,纬度,高度 </coordinates> </LinearRing> </outerBoundaryIs> </Polygon>`”  
\* 注：当 wpml:facadeWaylineEnable 为 1 时，测区多边形支持空中面，如“`<Polygon> <outerBoundaryIs> <LinearRing> <coordinates> 经度,纬度,300 经度,纬度,200 经度,纬度,50 </coordinates> </LinearRing> </outerBoundaryIs> </Polygon>`”，航线生成方向与端点顺序有关 | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:mappingHeadingParam | 建图航拍飞行器朝向参数 | \- | \- | \- | \- | M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:gimbalPitchMode | 云台俯仰角模式 | 枚举-string | \- | manual：手动控制云台俯仰角  
fixed：固定为用户设置的俯仰角 | \- | M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:gimbalPitchAngle | 云台俯仰角度 | ° | \- | \[-90, -30\] | \*注：当wpml:gimbalPitchMode为fixed时，该值为必需元素 | M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:quickOrthoMappingEnable | 正射智能摆拍开关 | 布尔型 | \- | 0：不开启  
1：开启，飞行器在单次建图航拍任务过程中，可通过云台摆动完成正射拍摄 | \- | M4E |
| wpml:quickOrthoMappingPitch | 正射智能摆拍角度 | ° | \- | \[10, 30\] | \*注：当wpml:quickOrthoMappingEnable时，该值为必需元素 | M4E |

#### [#](#倾斜摄影模板元素-父元素-placemark) 倾斜摄影模板元素（父元素：`<Placemark>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:caliFlightEnable | 是否开启标定飞行  
\* 注：仅适用于M300 RTK与M350 RTK机型 | 布尔型 | \- | 0：不开启  
1：开启，航线中自动进行惯导标定，保证模型精度。航线收尾会进行三次加减速飞行，航线拐弯处自动外扩进行加减速飞行。航线过长会均匀插入加减速飞行，每次标定后飞行时间不会超过100s。 | \- | M300 RTK，M350 RTK |
| wpml:inclinedGimbalPitch | 云台俯仰角度（倾斜） | 整型 | ° | \* 注：不同云台可转动范围不同。倾斜摄影模板会被生成五条航线，其中1条采集正射影像，4条采集倾斜影像。此元素用于设置倾斜影像采集时云台俯仰角度。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:inclinedFlightSpeed | 航线飞行速度（倾斜） | 浮点型 | m/s | \[1,15\]  
\* 注：倾斜摄影模板会被生成五条航线，其中1条采集正射影像，4条采集倾斜影像。此元素用于设置倾斜影像采集时飞行目标速度。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:shootType | 拍照模式（定时或定距） | 枚举-string | \- | time：等时间拍照  
distance：等间隔拍照\* 注：建议使用“time”等时间拍照。在template.kml文件中定义“拍照模式”、“重叠率”和“飞行速度”，计算后得出间隔时间或间隔距离距离写入waylines.wpml中。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:direction | 航线方向 | 整型 | ° | \[0, 360\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:margin | 测区外扩距离 | 整型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:overlap | 重叠率参数 | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:ellipsoidHeight | 全局航线高度（椭球高）  
\* 注：该元素与“wpml:height”配合使用，二者是同一位置不同高程参考平面的表达。 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:height | 全局航线高度（EGM96海拔高/相对起飞点高度/AGL相对地面高度）  
\* 注：该元素与“wpml:ellipsoidHeight”配合使用，二者是同一位置不同高程参考平面的表达。 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| Polygon | 测区多边形  
\* 注：此处格式如“`<Polygon> <outerBoundaryIs> <LinearRing> <coordinates> 经度,纬度,0 经度,维度,0 经度,纬度,0 </coordinates> </LinearRing> </outerBoundaryIs> </Polygon>`” | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

#### [#](#航带飞行模板元素-父元素-placemark) 航带飞行模板元素（父元素：`<Placemark>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:caliFlightEnable | 是否开启标定飞行 | 布尔型 | \- | 0：不开启  
1：开启 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:shootType | 拍照模式（定时或定距） | 枚举-string | \- | time：等时间拍照  
distance：等间隔拍照\* 注：建议使用“time”等时间拍照。在template.kml文件中定义“拍照模式”、“重叠率”和“飞行速度”，计算后得出间隔时间或间隔距离距离写入waylines.wpml中。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:direction | 航线方向 | 整型 | ° | \[0, 360\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:margin | 测区外扩距离 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:singleLineEnable | 是否开启单航线飞行 | 布尔型 | \- | 0：不开启  
1：开启 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:cuttingDistance | 每个子航带航线长度 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:boundaryOptimEnable | 是否开启边缘优化 | 布尔型 | \- | 0：不开启  
1：开启 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:leftExtend | 航带左侧外扩距离 | 整型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:rightExtend | 航带右侧外扩距离 | 整型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:includeCenterEnable | 是否包含中心线 | 布尔型 | \- | 0：不包含  
1：包含 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:overlap | 重叠率参数 | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:ellipsoidHeight | 全局航线高度（椭球高）  
\* 注：该元素与“wpml:height”配合使用，二者是同一位置不同高程参考平面的表达。 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:height | 全局航线高度（EGM96海拔高/相对起飞点高度/AGL相对地面高度）  
\* 注：该元素与“wpml:ellipsoidHeight”配合使用，二者是同一位置不同高程参考平面的表达。 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:stripUseTemplateAltitude | 是否开启变高航带  
_注：该元素与“LineString”配合使用，开启后将按照椭球高读取其中的高度值。_ | 布尔型 | \- | 0：不开启  
1：开启 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| LineString | 航点信息  
_注：格式为 “`<LineString> <coordinates> 经度,纬度,高度 经度,维度,高度 经度,纬度,高度 </coordinates> </LineString>`”。其中高度值仅在 “wpml:stripUseTemplateAltitude” 开启时读取。_ | \- | \- | \- | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

#### [#](#航点信息-父元素-placemark) 航点信息（父元素：`<Placemark>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:isRisky | 是否危险点 | 布尔型 | \- | 0：正常点，1：危险点 | \- | M30/M30T，M3D/M3TD，M4E/M4T |
| Point | 航点经纬度<经度,纬度>  
\* 注：此处格式如“`<Point> <coordinates> 经度,纬度 </coordinates> </Point>`” | 浮点型 | °,° | \[-180,180\],\[-90,90\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:index | 航点序号  
\* 注：在一条航线内该ID唯一。该序号必须从0开始单调连续递增。 | 整型 | \- | \[0, 65535\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:useGlobalHeight | 是否使用全局高度 | 布尔型 | \- | 0, 1 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:ellipsoidHeight | 航点高度（WGS84椭球高度）  
\* 注：该元素与“wpml:height”配合使用，二者是同一位置不同高程参考平面的表达。 | 浮点型 | m | \- | 必需元素  
\* 注：当且仅当“wpml:useGlobalHeight”为“0”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:height | 航点高度（EGM96海拔高度/相对起飞点高度/AGL相对地面高度）  
\* 注：该元素与“wpml:ellipsoidHeight”配合使用，二者是同一位置不同高程参考平面的表达。 | 浮点型 | m | \- | 必需元素  
\* 注：当且仅当“wpml:useGlobalHeight”为“0”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:useGlobalSpeed | 是否使用全局飞行速度  
\* 注：此处的全局飞行速度即“wpml:autoFlightSpeed” | 布尔型 | \- | 0：不使用全局设置  
1：使用全局设置 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:waypointSpeed | 航点飞行速度 | 浮点型 | m/s | \[1,15\]  
 | 必需元素  
\* 注：当且仅当“wpml:useGlobalSpeed”为“0”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:useGlobalHeadingParam | 是否使用全局偏航角模式参数 | 布尔型 | \- | 0：不使用全局设置  
1：使用全局设置 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:waypointHeadingParam | 偏航角模式参数 | \- | \- | \- | 必需元素  
\* 注：当且仅当“wpml:useGlobalHeadingParam”为“0”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:useGlobalTurnParam | 是否使用全局航点类型（全局航点转弯模式） | 布尔型 | \- | 0：不使用全局设置  
1：使用全局设置 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:waypointTurnParam | 航点类型（航点转弯模式） | \- | \- | \- | 必需元素  
\* 注：当且仅当“wpml:useGlobalTurnParam”为“0”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:useStraightLine | 该航段是否贴合直线 | 布尔型 | \- | 0：航段轨迹全程为曲线  
1：航段轨迹尽量贴合两点连线 | 必需元素  
\* 注：当且仅当“wpml:waypointTurnParam”内"waypointTurnMode"被设置为“toPointAndStopWithContinuityCurvature”或“toPointAndPassWithContinuityCurvature”时必需。如果此元素被设置，则局部定义会覆盖全局定义。 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:gimbalPitchAngle | 航点云台俯仰角 | 浮点型 | ° | 对应机型云台可转动范围 | 必需元素  
\* 注：当且仅当“wpml:gimbalPitchMode”为“usePointSetting”时必需。 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

#### [#](#坐标系参数信息-父元素-wpml-waylinecoordinatesysparam) 坐标系参数信息（父元素：`<wpml:waylineCoordinateSysParam>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:coordinateMode | 经纬度坐标系 | 枚举-string | \- | WGS84：当前固定使用  
WGS84坐标系 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:heightMode | 航点高程参考平面 | 枚举-string | \- | EGM96：使用海拔高编辑  
relativeToStartPoint：使用相对点的高度进行编辑  
aboveGroundLevel：使用地形数据，AGL下编辑(仅支持司空2平台)  
realTimeFollowSurface: 使用实时仿地模式（仅用于建图航拍模版），仅支持M3E/M3T/M3M机型 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:positioningType | 经纬度与高度数据源 | 枚举-string | \- | GPS：位置数据采集来源为GPS/BDS/GLONASS/GALILEO等  
RTKBaseStation：采集位置数据时，使用RTK基站进行差分定位  
QianXun：采集位置数据时，使用千寻网络RTK进行差分定位  
Custom：采集位置数据时，使用自定义网络RTK进行差分定位 | 无，非必需元素  
\* 注：该元素仅用于标记位置数据来源，不影响实际航线执行。 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:globalShootHeight | 飞行器离被摄面高度（相对地面高）  
\* 注：仅适用于模板类型mapping2d，mapping3d，mappingStrip | 浮点型 | m | 用于计算拍照间距和GSD | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:surfaceFollowModeEnable | 是否开启仿地飞行  
\* 注：仅适用于模板类型mapping2d，mapping3d，mappingStrip | 布尔型 | \- | 0：不开启1：开启 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:surfaceRelativeHeight | 仿地飞行离地高度（相对地面高）  
\* 注：仅适用于模板类型mapping2d，mapping3d，mappingStrip | 浮点型 | m | \- | 必需元素  
\* 注：当且仅当“wpml:surfaceFollowModeEnable”为“1”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

#### [#](#重叠率信息-父元素-wpml-overlap) 重叠率信息（父元素`<wpml:overlap>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:orthoLidarOverlapH | 激光航向重叠率（正射） | 整型 | 百分比 | \[0, 100\] | \- | M300 RTK，M350 RTK |
| wpml:orthoLidarOverlapW | 激光旁向重叠率（正射） | 整型 | 百分比 | \[0, 100\] | \- | M300 RTK，M350 RTK |
| wpml:orthoCameraOverlapH | 可见光航向重叠率（正射） | 整型 | 百分比 | \[0, 100\] | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:orthoCameraOverlapW | 可见光旁向重叠率（正射） | 整型 | 百分比 | \[0, 100\] | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:inclinedLidarOverlapH | 激光航向重叠率（倾斜） | 整型 | 百分比 | \[0, 100\] | \- | M300 RTK，M350 RTK |
| wpml:inclinedLidarOverlapW | 激光旁向重叠率（倾斜） | 整型 | 百分比 | \[0, 100\] | \- | M300 RTK，M350 RTK |
| wpml:inclinedCameraOverlapH | 可见光航向重叠率（倾斜） | 整型 | 百分比 | \[0, 100\] | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:inclinedCameraOverlapW | 可见光旁向重叠率（倾斜） | 整型 | 百分比 | \[0, 100\] | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

#### [#](#建图航拍飞行器朝向参数-父元素-wpml-mappingheadingparam) 建图航拍飞行器朝向参数（父元素`<wpml:mappingHeadingParam>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:mappingHeadingMode | 飞行器偏航角模式 | 枚举-string | \- | fixed:固定为用户设置的偏航角  
followWayline:偏航角跟随航线 | \- | M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:mappingHeadingAngle | 飞行器偏航角 | 整型 | ° | \[0, 360\] | \*注：当wpml:mappingHeadingMode为fixed时，该值为必需元素 | M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

[Github Edit open in new window](https://github.com/dji-sdk/Cloud-API-Doc/blob/master/docs/cn/60.api-reference/00.dji-wpml/20.template-kml.md)

最近修改: 2025/2/25 20:24:49

[总体介绍](/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/overview.html) [waylines.wpml 说明](/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/waylines-wpml.html)

请问您的文档阅读体验如何？