package be.technifutur.reservation.models.entities;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private UUID ref = UUID.randomUUID();
    private LocalDate date_depart;
    private LocalDate date_arrive;
    private Status status = Status.DEMANDE;

    public Reservation(LocalDate arrive, LocalDate depart) {
        this.date_arrive = arrive;
        this.date_depart = depart;
    }

    public static enum Status{
        DEMANDE,
        FACTURE
    }
    
}
