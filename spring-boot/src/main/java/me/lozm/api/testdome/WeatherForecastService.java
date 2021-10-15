package me.lozm.api.testdome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Provider;

@FunctionalInterface
interface TemperatureMeasurementCallback {
    public void temperatureMeasured(int temperature);
}

interface Thermometer {
    public int measure();
}

@EnableScheduling
@Configuration
@Import({FakeThermometer.class, WeatherForecastService.class})
class TestConfig2 {

    @Bean
    public TemperatureMeasurementCallback callback() {
        return (temperature) -> System.out.println(temperature);
    }
}

@Scope("prototype")
@Component
class FakeThermometer implements Thermometer {

    private int currentTemperature = 21;

    @Override
    public int measure() {
        return (currentTemperature++);
    }

}

@Service
public class WeatherForecastService {

    @Autowired
    private Thermometer thermometer;
    @Autowired
    private TemperatureMeasurementCallback callback;

//    @Lookup
//    public Thermometer getThermometer() {
//        return new FakeThermometer();
//    }

    @Scheduled(fixedRate = 50)
    public void takeTemperatureMeasurement() {
        int temperature = thermometer.measure();
        callback.temperatureMeasured(temperature);
    }

}
