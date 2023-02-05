package daneker.code.weathersensor.controllers;

import daneker.code.weathersensor.DTO.sensorDTO;
import daneker.code.weathersensor.entity.Sensor;
import daneker.code.weathersensor.service.sensorService;
import daneker.code.weathersensor.util.MeasurementErrorResponse;
import daneker.code.weathersensor.util.MeasurementException;
import daneker.code.weathersensor.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static daneker.code.weathersensor.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class sensorsController {

    private final sensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    public sensorsController(sensorService sensorService,
                              ModelMapper modelMapper,
                              SensorValidator sensorValidator) {

        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;

    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid sensorDTO sensorDTO ,
                                                   BindingResult bindingResult){
        Sensor sensorToSend = convertToSensor(sensorDTO);
        sensorValidator.validate(sensorToSend , bindingResult);

        if(bindingResult.hasErrors()){
            returnErrorsToClient(bindingResult);
        }

        sensorService.save(sensorToSend);
        return ResponseEntity.ok(HttpStatus.OK);

    }


    private Sensor convertToSensor(sensorDTO sensorDTO){
      return  modelMapper.map(sensorDTO , Sensor.class);
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException exception){
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse,HttpStatus.BAD_REQUEST);
    }


}
