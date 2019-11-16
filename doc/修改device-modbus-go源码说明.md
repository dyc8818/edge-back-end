# 所有修改都在internal/driver目录下

1. 修改constant.go文件

   添加int8和uint8

   修改后文件如下

   ```go
   // -*- Mode: Go; indent-tabs-mode: t -*-
   //
   // Copyright (C) 2018-2019 IOTech Ltd
   //
   // SPDX-License-Identifier: Apache-2.0
   
   package driver
   
   import sdkModel "github.com/edgexfoundry/device-sdk-go/pkg/models"
   
   const (
   	BOOL = "BOOL"
   
   	INT8 = "INT8"
   	INT16 = "INT16"
   	INT32 = "INT32"
   	INT64 = "INT64"
   
   	UINT8 = "UINT8"
   	UINT16 = "UINT16"
   	UINT32 = "UINT32"
   	UINT64 = "UINT64"
   
   	FLOAT32 = "FLOAT32"
   	FLOAT64 = "FLOAT64"
   
   	DISCRETES_INPUT   = "DISCRETES_INPUT"
   	COILS             = "COILS"
   	INPUT_REGISTERS   = "INPUT_REGISTERS"
   	HOLDING_REGISTERS = "HOLDING_REGISTERS"
   
   	PRIMARY_TABLE    = "primaryTable"
   	STARTING_ADDRESS = "startingAddress"
   	// RAW_TYPE define binary data type which read from Modbus device
   	RAW_TYPE = "rawType"
   )
   
   var PrimaryTableBitCountMap = map[string]uint16{
   	DISCRETES_INPUT:   1,
   	COILS:             1,
   	INPUT_REGISTERS:   16,
   	HOLDING_REGISTERS: 16,
   }
   
   var ValueTypeBitCountMap = map[sdkModel.ValueType]uint16{
   	sdkModel.Int8: 8,
   	sdkModel.Int16: 16,
   	sdkModel.Int32: 32,
   	sdkModel.Int64: 64,
   
   	sdkModel.Uint8: 8,
   	sdkModel.Uint16: 16,
   	sdkModel.Uint32: 32,
   	sdkModel.Uint64: 64,
   
   	sdkModel.Float32: 32,
   	sdkModel.Float64: 64,
   
   	sdkModel.Bool: 1,
   }
   
   ```

   

2. 修改deviceclient.go文件

   其中的TransformDataBytesToResult函数需要添加uint和int两项

   ```go
   case sdkModel.Uint8:
   		var res = uint8( (dataBytes)[0])
   		result, err = sdkModel.NewUint8Value(req.DeviceResourceName, resTime, res)
   	case sdkModel.Int8:
   		var res = int8( (dataBytes)[0])
   		result, err = sdkModel.NewInt8Value(req.DeviceResourceName, resTime, res)
   
   ```
   函数 TransformCommandValueToDataBytes 修改如下

   ```go
   var dataBytes []byte
   	if maxSize>=byteCount {
   		driver.Logger.Info("true")
   		dataBytes = value.NumericValue[maxSize-byteCount : maxSize]
   	}else{
   		driver.Logger.Info("else")
   		dataBytes = value.NumericValue[0:maxSize]
   	}
   ```

   

3. 重新编译即可使用