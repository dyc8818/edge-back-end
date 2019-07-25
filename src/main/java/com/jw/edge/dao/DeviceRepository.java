package com.jw.edge.dao;

import com.jw.edge.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends MongoRepository<Device,String> {
    public Device findDeviceByDeviceName(String deviceName);
    public Page<Device> findAll(Pageable pageable);
    public Device findDeviceByDeviceId(String deviceId);
    public Page<Device> findDeviceByDeviceType(String deviceType, Pageable pageable);
    List<Device> findAll();
}
