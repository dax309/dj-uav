waylines.wpml 说明
================

2025-02-25

4.2 分

15 用户已评分

[Github Edit open in new window](https://github.com/dji-sdk/Cloud-API-Doc/blob/master/docs/cn/60.api-reference/00.dji-wpml/30.waylines-wpml.md)

*   [文件介绍](/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/waylines-wpml.html#文件介绍)
*   [元素说明](/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/waylines-wpml.html#元素说明)

[#](#文件介绍) 文件介绍
---------------

`waylines.wpml`是飞机直接执行的文件，它定义了明确的无人机飞行和负载动作指令，这些指令由 DJI Pilot 2、DJI Flighthub 2 或者其它软件生成，也可被开发者直接编辑开发。`waylines.wpml`文件由两部分组成：

1.  任务信息：主要包含 `wpml:missionConfig`元素，定义航线任务的全局参数等。
2.  航线信息：主要包含`Folder`元素，定义详细的航线信息（路径定义、动作定义等）。每个`Folder`代表一条可执行的航线。特别的，当使用“倾斜摄影”模板时，将生成5条可执行航线，对应`waylines.wpml`内的5个`Folder`元素。

### [#](#示例文件) 示例文件

`waylines.wpml`示例文件如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:wpml="http://www.dji.com/wpmz/1.0.2">
  <Document>
    <!-- Step 1: Setup Mission Configuration -->
    <wpml:missionConfig>
      <wpml:flyToWaylineMode>safely</wpml:flyToWaylineMode>
      <wpml:finishAction>goHome</wpml:finishAction>
      <wpml:exitOnRCLost>goContinue</wpml:exitOnRCLost>
      <wpml:executeRCLostAction>hover</wpml:executeRCLostAction>
      <wpml:takeOffSecurityHeight>20</wpml:takeOffSecurityHeight>
      <wpml:globalTransitionalSpeed>10</wpml:globalTransitionalSpeed>
      <!-- Declare drone model with M30 -->
      <wpml:droneInfo>
        <wpml:droneEnumValue>67</wpml:droneEnumValue>
        <wpml:droneSubEnumValue>0</wpml:droneSubEnumValue>
      </wpml:droneInfo>
      <!-- Declare drone model with M30 -->
      <wpml:payloadInfo>
        <wpml:payloadEnumValue>52</wpml:payloadEnumValue>
        <wpml:payloadPositionIndex>0</wpml:payloadPositionIndex>
      </wpml:payloadInfo>
    </wpml:missionConfig>

    <!-- Step 2: Setup A Folder for Waypoint Template -->
    <Folder>
      <wpml:templateId>0</wpml:templateId>
      <wpml:executeHeightMode>WGS84</wpml:executeHeightMode>
      <wpml:waylineId>0</wpml:waylineId>
      <wpml:autoFlightSpeed>10</wpml:autoFlightSpeed>
      <Placemark>
        <Point>
          <coordinates>
            longitude,latitude
          </coordinates>
        </Point>
        <wpml:index>0</wpml:index>
        <wpml:executeHeight>116.57</wpml:executeHeight>
        <wpml:waypointSpeed>10</wpml:waypointSpeed>
        <wpml:waypointHeadingParam>
          <wpml:waypointHeadingMode>followWayline</wpml:waypointHeadingMode>
        </wpml:waypointHeadingParam>
        <wpml:waypointTurnParam>
          <wpml:waypointTurnMode>toPointAndStopWithDiscontinuityCurvature</wpml:waypointTurnMode>
          <wpml:waypointTurnDampingDist>0</wpml:waypointTurnDampingDist>
        </wpml:waypointTurnParam>
      </Placemark>
      <Placemark>
        <Point>
          <coordinates>
            longitude,latitude
          </coordinates>
        </Point>
        <wpml:index>1</wpml:index>
        <wpml:executeHeight>116.57</wpml:executeHeight>
        <wpml:waypointSpeed>7</wpml:waypointSpeed>
        <wpml:waypointHeadingParam>
          <wpml:waypointHeadingMode>followWayline</wpml:waypointHeadingMode>
        </wpml:waypointHeadingParam>
        <wpml:waypointTurnParam>
          <wpml:waypointTurnMode>toPointAndStopWithDiscontinuityCurvature</wpml:waypointTurnMode>
          <wpml:waypointTurnDampingDist>0</wpml:waypointTurnDampingDist>
        </wpml:waypointTurnParam>
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

### [#](#任务信息-父元素-wpml-missionconfig) 任务信息（父元素：`<wpml:missionConfig>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:flyToWaylineMode | 飞向首航点模式 | 枚举-string | \- | safely：安全模式  
（M300）飞行器起飞，上升至首航点高度，再平飞至首航点。如果首航点低于起飞点，则起飞后平飞至首航点上方再下降。  
（M30）飞行器起飞，上升至首航点高度，再平飞至首航点。如果首航点低于“安全起飞高度”，则起飞至“安全起飞高度”后，平飞至首航点上方再下降。注意“安全起飞高度”仅在飞行器未起飞时生效。  
  
pointToPoint：倾斜飞行模式  
（M300）飞行器起飞后，倾斜飞到首航点。  
（M30）飞行器起飞至“安全起飞高度”，再倾斜爬升至首航点。如果首航点高度低于“安全起飞高度”，则先平飞后下降。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:finishAction | 航线结束动作 | 枚举-string | \- | goHome：飞行器完成航线任务后，退出航线模式并返航。  
noAction：飞行器完成航线任务后，退出航线模式。  
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
| wpml:droneInfo | 飞行器机型信息 | \- | \- | _注：请在[共用元素信息open in new window](https://developer.dji.com/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/common-element.html)章节阅读详细信息_ | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:payloadInfo | 负载机型信息 | \- | \- | _注：请在[共用元素信息open in new window](https://developer.dji.com/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/common-element.html)章节阅读详细信息_ | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:autoRerouteInfo | 航线绕行 | \- | \- | \- | \- | M3D/M3TD，M4E/M4T |

### [#](#航线信息-父元素-folder) 航线信息（父元素：`<Folder>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:templateId | 模板ID  
\* 注：在一个kmz文件内该ID唯一。建议从0开始单调连续递增。在template.kml和waylines.wpml文件中，将使用该id将模板与所生成的可执行航线进行关联。 | 整型 | \- | \[0, 65535\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:waylineId | 航线ID  
\* 注：在一条航线中该ID唯一。建议从0开始单调连续递增。 | 整型 | \- | \[0, 65535\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:autoFlightSpeed | 全局航线飞行速度 | 浮点型 | m/s | \[1,15\]  
\* 注：此元素定义了此模板生成的整段航线中，飞行器的目标飞行速度。如果额外定义了某航点的该元素，则局部定义会覆盖全局定义。 | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:executeHeightMode | 执行高度模式  
\* 注：该元素仅在waylines.wpml中使用。 | 枚举-string | \- | WGS84：椭球高模式  
relativeToStartPoint：相对起飞点高度模式  
realTimeFollowSurface: 使用实时仿地模式，仅支持M3E/M3T/M3M | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| Placemark(Point) | 航点信息（包括航点经纬度和高度等） | \- | \- | 请阅读文档内容，航线文件格式 > template.kml 说明 > Placemark | \- | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:startActionGroup | 航线初始动作  
\*注：该元素用于规划一系列初始动作，在航线开始前执行。航线中断恢复时，先执行初始动作，再执行航点动作 | \- | \- | 拓展阅读：[共用元素信息open in new window](https://developer.dji.com/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/common-element.html)中的 `<wpml:actionGroup>` | \- | M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

### [#](#航点信息-placemark) 航点信息（`<Placemark>`）

| 元素 | 名称 | 类型 | 单位 | 取值与释义 | 是否必需（默认值） | 支持机型 |
| --- | --- | --- | --- | --- | --- | --- |
| wpml:isRisky | 是否危险点 | 布尔型 | \- | 0：正常点，1：危险点 | \- | M30/M30T，M3D/M3TD，M4E/M4T |
| Point | 航点经纬度<经度,纬度>  
\* 注：此处格式如“`<Point> <coordinates> 经度,纬度 </coordinates> </Point>`” | 浮点型 | °,° | \[-180,180\],\[-90,90\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:index | 航点序号  
\* 注：在一条航线内该ID唯一。该序号必须从0开始单调连续递增。 | 整型 | \- | \[0, 65535\] | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:executeHeight | 航点执行高度  
\* 注：该元素仅在waylines.wpml中使用。具体高程参考平面在“wpml:executeHeightMode”中声明。 | 浮点型 | m | \- | 必需元素 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:waypointSpeed | 航点飞行速度，当前航点飞向下一个航点的速度 | 浮点型 | m/s | \[1, 15\] | 必需元素  
\* 注：当且仅当“wpml:useGlobalSpeed”为“0”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:waypointHeadingParam | 偏航角模式参数 | \- | \- | \- | 必需元素  
\* 注：当且仅当“wpml:useGlobalHeadingParam”为“0”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:waypointTurnParam | 航点类型（航点转弯模式） | \- | \- | \- | 必需元素  
\* 注：当且仅当“wpml:useGlobalTurnParam”为“0”时必需 | M300 RTK，M350 RTK，M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |
| wpml:useStraightLine | 该航段是否贴合直线 | 布尔型 | \- | 0：航段轨迹全程为曲线  
1：航段轨迹尽量贴合两点连线 | 必需元素  
\* 注：当且仅当“wpml:waypointTurnParam”内"waypointTurnMode"被设置为“toPointAndStopWithContinuityCurvature”或“toPointAndPassWithContinuityCurvature”时必需。如果此元素被设置，则局部定义会覆盖全局定义。 | M30/M30T，M3E/M3T/M3M，M3D/M3TD，M4E/M4T |

[Github Edit open in new window](https://github.com/dji-sdk/Cloud-API-Doc/blob/master/docs/cn/60.api-reference/00.dji-wpml/30.waylines-wpml.md)

最近修改: 2025/2/25 20:24:49

[template.kml 说明](/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/template-kml.html) [共用元素信息](/doc/cloud-api-tutorial/cn/api-reference/dji-wpml/common-element.html)

请问您的文档阅读体验如何？