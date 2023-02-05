package daneker.code.weathersensor.repository;

import daneker.code.weathersensor.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface SensorRepository extends JpaRepository<Sensor , Integer> {
    Optional<Sensor> findByName(String name);
}
