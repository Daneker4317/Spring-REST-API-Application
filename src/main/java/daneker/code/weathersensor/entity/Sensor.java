package daneker.code.weathersensor.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


@Entity
@Table(name = "sensor")
public class Sensor implements Serializable { // we have to implement interface Serializable because
    // we use name as table measurement reference to name of sensor not to primary key id
    // we create relation  "One to many" through name of sensors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    private Integer id;

    @NotEmpty(message = "name of sensor should not be empty")
    @Size(min = 2,
            max = 30,
            message = "sensor name should be between 2 - 30 characters")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
