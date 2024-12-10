package com.ogb.auth.service;

import com.ogb.auth.entity.SensorData;
import com.ogb.auth.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public SensorData addOrUpdateSensorData(SensorData sensorData) {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        // Verifica se já existe um registro com a mesma data
        List<SensorData> existingData = sensorDataRepository.findByData(nowDate);

        if (!existingData.isEmpty()) {
            // Se existir, atualiza os valores
            SensorData existingSensorData = existingData.get(0);
            existingSensorData.setTemperatura(sensorData.getTemperatura());
            existingSensorData.setGas(sensorData.getGas());
            existingSensorData.setHora(nowTime); // Atualiza a hora, se necessário
            return sensorDataRepository.save(existingSensorData);
        } else {
            // Se não existir, insere um novo registro
            sensorData.setData(nowDate);
            sensorData.setHora(nowTime);
            return sensorDataRepository.save(sensorData);
        }
    }

    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }

    public List<LocalDate> getAllDatas() {
        return sensorDataRepository.findAll().stream()
                .map(SensorData::getData)
                .distinct()
                .collect(Collectors.toList());
    }

    public SensorData getSensorDataByDate(LocalDate data) {
        List<SensorData> existingData = sensorDataRepository.findByData(data);
        return existingData.isEmpty() ? null : existingData.get(0);
    }
}
