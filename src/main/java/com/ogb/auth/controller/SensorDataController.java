package com.ogb.auth.controller;

import com.ogb.auth.entity.SensorData;
import com.ogb.auth.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sensores")
public class SensorDataController {

    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping("/add")
    public SensorData addSensorData(@RequestBody SensorData sensorData) {
        return sensorDataService.addOrUpdateSensorData(sensorData);
    }

    @GetMapping("/todos")
    public List<SensorData> getAllSensorData() {
        return sensorDataService.getAllSensorData();
    }

    @GetMapping("/todas-datas")
    public List<LocalDate> getAllDatas() {
        return sensorDataService.getAllDatas(); // Implementar no serviço
    }

    @GetMapping("/data/{data}")
    public SensorData getSensorDataByDate(@PathVariable LocalDate data) {
        return sensorDataService.getSensorDataByDate(data); // Implementar no serviço
    }
}
