package net.nwmissouri.springbootkafka.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@lombok.Data
@AllArgsConstructor
@Table(name="Data")
@Entity
public class Data implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private double temp;

    private String city_name;

    private LocalDateTime localDateTime;

    @Transient
    private LocalDateTime startTime;

    private double wind_spd;

    public double getWind_spd() {
        return wind_spd;
    }

    public void setWind_spd(double wind_spd) {
        this.wind_spd = wind_spd;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Data() {
    }

    public Data(double temp, String city_name) {
        this.temp = temp;
        this.city_name = city_name;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "temp=" + temp +
                ", city_name='" + city_name + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
