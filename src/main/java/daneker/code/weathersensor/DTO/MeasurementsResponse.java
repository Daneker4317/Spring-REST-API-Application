package daneker.code.weathersensor.DTO;

import daneker.code.weathersensor.entity.Measurement;

import java.util.List;

public class MeasurementsResponse {
    private List<measurementDTO> measurements;


    public MeasurementsResponse(List<measurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<measurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<measurementDTO> measurements) {
        this.measurements = measurements;
    }
}
