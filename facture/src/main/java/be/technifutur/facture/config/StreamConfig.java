package be.technifutur.facture.config;

import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.technifutur.facture.models.Facture;
import be.technifutur.facture.models.Reservation;
import be.technifutur.facture.services.FactureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StreamConfig {
    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private FactureService service;

    @Bean
    public Function<Reservation,Facture> recevoireservationenvoifacture(){
        return (r) ->{
            log.info("Reservation RECEIVED - " + r);

            Facture f = service.createFacture((int)r.getDate_arrive().until(r.getDate_depart(),ChronoUnit.DAYS), r.getRef());
            return f;
        };
    }
}
