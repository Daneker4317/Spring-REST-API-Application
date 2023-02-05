package daneker.code.weathersensor.service;

import daneker.code.weathersensor.entity.Sensor;
import daneker.code.weathersensor.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.OptionalInt;

@Service
@Transactional(readOnly = true)
public class sensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public sensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }
}
