package daneker.code.weathersensor.repository;

import daneker.code.weathersensor.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement , Integer> {
}
