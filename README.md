# 平台后端

## 实时监控
### 实时监控实体类
目前暂未使用
### 实时监控数据访问对象层
目前暂未使用
### 实时监控服务层  
SurveillanceServiceImplement类实现了SurveillanceService接口
+ getOnlineDevices()  
获得成功与edgex连接并能调用其命令的在线设备信息。  
返回JSON数组  
```
    [
     {"id":(String) 设备id,
      "name":(String) 设备名称
     },
     {...},{...}...
    ]
```
    
+ getTotalNum()  
获取在edgex中有注册信息的设备总数。  
返回int。

+ getDeviceDetail(String id)  
获取特定设备的详细读数。  
Param: String id 此设备在edgex中的注册id。  
返回一个JSON对象   
```
 {(String) 读数名称1：(int) 值1,
  (String) 读数名称2:(int) 值2,
  ...}
```

+ getExpiringDevice()  
获取在后端数据库中即将到期的设备详情。  
返回一个JSON数组  
```
[{
 "name":(String) 设备名称,
 "failDate":(String) 到期日期,
 "description":(String) 描述},
 {...}...]
```

+ getRegNum()  
获取在后端数据库中注册的设备总数。  
返回int。

+ getRegDevice()  
获取在后端数据库中注册的设备详情。  
返回一个JSON数组  
```
[{
 "id":(String) 后端数据库中的设备id,
 "name";(String) 设备名称,
 "type":(String) 设备模板类型,
 "description":(String) 描述
 },
 {...}...]
```

+ getAge()  
获取在后端数据库中设备寿命的分布情况。  
返回一个JSON对象  
```
{"rookieGood":(int) 新注册且状态良好的数量,
 "rookieFix":(int) 新注册但需要维修更换的数量,
 "veteranGood":(int) 正常设备数量,
 "veteranFix":(int) 长时工作后需要更换维修的设备数量
}
```
### 实时监控控制器层
此控制器所有调用的方法来自于实时监控服务层。

+ getSurNum()  
*http://localhost:8080/api/surnum*  
调用getOnlineDevices()，根据返回的数组判断在线设备数量，返回int值。

+ getId()  
*http://localhost:8080/api/surid*  
调用getOnlineDevices()，返回在线设备信息，返回值同getOnlineDevices()。

+ getTotalNum()  
*http://localhost:8080/api/totalnum*   
调用getTotalNum()，获取edgex中注册设备总数，返回int。

+ getDetails(@RequestParam String id)  
*http://localhost:8080/api/surdetails?id=...*  
调用getDetails()，返回值同getDetails()。

+ getExpiringNum()  
*http://localhost:8080/api/expiringnum*  
调用getExpiringDevice()，统计数组数量得到需维修更换设备总数，返回int值。

+ getExpiring(@RequestParam Integer page, @RequestParam Integer limit)  
*http://localhost:8080/api/expiringdetails?page=1&limit=30*  
由于Layui特性，http请求中page和limit参数需存在但并不生效。  
调用getExpiringDevice()，
返回Layui数据表格可接收形式的将过期设备信息。

+ getRegNum()  
*http://localhost:8080/api/regnum*  
调用getRegNum()，返回注册设备数量，int值。

+ getReg(@RequestParam Integer page, @RequestParam Integer limit)  
*http://localhost:8080/api/regdetails?page=1&limit=30*  
由于Layui特性，http请求中page和limit参数需存在但并不生效。  
调用getRegDevice()，
返回Layui数据表格可接收形式的将已注册设备信息。

+ getAgeDistribution()  
*http://localhost:8080/api/agedis*  
调用getAge()，返回值同getAge()。  

# 数据库

# 界面

# 安全

## 1.添加spring security依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## 2.启动spring security

```java
@EnableWebSecurity
```

