# 1. 添加profile

post方法

http://202.205.102.91:48081/api/v1/deviceprofile/upload

然后可以用body，form形式的上传文件（可能会出现问题）

也可以用raw的test(json)传



```yml
name: relay
manufacturer: xx
model: ModBus-1
labels: [relay]
description: relay profile
deviceResources: 
- description: relay
  name: Relay
  properties:
    value:
      type: INT16
      readWrite: R
      defaultValue: "0"
      size: "1"
      scale: "0.1"
      mediatype: ""
    units:
      type: String
      readWrite: R
  attributes:
    primaryTable: HOLDING_REGISTERS
    startingAddress: "2"
deviceCommands:
- name: Relay
  get:
  - index: "1"
    operation: get
    object: Relay
    parameter: Relay
coreCommands:
- name: Relay
  get:
    path: /api/v1/device/{deviceId}/Relay
    responses:
    - code: "200"
      description: Get the Relay state
      expectedValues:
      - Relay
    - code: "503"
      description: service unavailable 
```

# 2. 添加设备

post http://202.205.102.91:48081/api/v1/device

注意大写

```json
{
    "description": "",
    "name": "relay device",
    "adminState": "UNLOCKED",
    "operatingState": "ENABLED",
    "protocols": {
        "modbus-tcp": {
            "Address": "192.168.1.2",
            "Port": "1030",
            "UnitID" : "1"
        }
    },
    "labels": [
        "relay",
        "Modbus RTU"
    ],	
    "service": {
        "name": "edgex-device-modbus",
        "adminState": "UNLOCKED"
    },
    "profile": {
        "name": "relay profile"
    }
}
```

# 3. 验证命令

先查询device

get  

```
http://202.205.102.91:48081/api/v1/device/name/relay device
```

可以获得命令id

再查询命令command

get  

```
http://202.205.102.91:48082/api/v1/device/0610de32-a715-4920-a08a-988e85b52c3f/command/da5d0a81-7281-4343-9dc7-256c1a27b63a
```

