package com.jw.edge.service;

import com.jw.edge.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceService {
    public Page<Device> findAllDevice(Pageable pageable);
    public List<Device> findAllDevice();
    public boolean addDevice(Device device);
    public String deleteDevice(String deviceId);
    public Device findDeviceByDeviceId(String deviceId);
    public Page<Device> findDeviceByDeviceType(String deviceType, Pageable pageable);
}

