package daneker.code.weathersensor.service;

import daneker.code.weathersensor.entity.Measurement;
import daneker.code.weathersensor.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class measurementService {
    private final MeasurementRepository measurementRepository;
    private final sensorService sensorService;

    @Autowired
    public measurementService(MeasurementRepository measurementRepository, sensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll(){return measurementRepository.findAll();}

    public void addMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }
    public void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementDateTime(LocalDateTime.now());
    }

}
