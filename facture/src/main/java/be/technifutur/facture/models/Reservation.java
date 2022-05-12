package be.technifutur.facture.models;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private UUID ref;
    private LocalDate date_depart;
    private LocalDate date_arrive;
    private Status status;

    public static enum Status{
        DEMANDE,
        FACTURE
    }
    
}
