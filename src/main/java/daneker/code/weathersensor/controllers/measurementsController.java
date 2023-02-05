package daneker.code.weathersensor.controllers;


import daneker.code.weathersensor.DTO.MeasurementsResponse;
import daneker.code.weathersensor.DTO.measurementDTO;
import daneker.code.weathersensor.entity.Measurement;
import daneker.code.weathersensor.service.measurementService;
import daneker.code.weathersensor.util.MeasurementErrorResponse;
import daneker.code.weathersensor.util.MeasurementException;
import daneker.code.weathersensor.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static daneker.code.weathersensor.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("measurements")
public class measurementsController{
    private final measurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;


    @Autowired
    public measurementsController(measurementService measurementService,
                                  MeasurementValidator measurementValidator,
                                  ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid measurementDTO measurementDTO ,
                                          BindingResult bindingResult){

        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement,bindingResult);

        if(bindingResult.hasErrors()){
            returnErrorsToClient(bindingResult);
        }

        measurementService.addMeasurement(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount(){
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    @GetMapping
    public MeasurementsResponse getMeasurements(){
        return new MeasurementsResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException exception){
        MeasurementErrorResponse errorResponse = new MeasurementErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return
                new ResponseEntity<>(errorResponse , HttpStatus.OK);
    }

    private measurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement , measurementDTO.class);
    }

    private Measurement convertToMeasurement(measurementDTO measurementDTO){
        return modelMapper.map(measurementDTO , Measurement.class);
    }

}
