package daneker.code.weathersensor.util;

import daneker.code.weathersensor.entity.Sensor;
import daneker.code.weathersensor.service.sensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final sensorService service;

    @Autowired
    public SensorValidator(sensorService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Sensor sensor = (Sensor) target;

        if(service.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name " , "sensor with this name registered yet");
        }

    }
}
