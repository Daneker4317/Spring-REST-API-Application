package daneker.code.weathersensor.util;

import daneker.code.weathersensor.entity.Measurement;
import daneker.code.weathersensor.service.sensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class MeasurementValidator implements Validator {

    private final sensorService service;

    @Autowired
    public MeasurementValidator(sensorService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if(measurement == null){
            return;
        }

        if(service.findByName(measurement.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor" , "sensor with this name not registered yet");
        }

    }
}
