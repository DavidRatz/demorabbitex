package be.technifutur.reservation.models.dtos;

import java.time.LocalDate;
import java.util.UUID;

import be.technifutur.reservation.models.entities.Reservation;
import be.technifutur.reservation.models.entities.Reservation.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationDTO {
    private final UUID ref;
    private final LocalDate date_arrive;
    private final LocalDate date_depart;
    private final Status status;
    private final Double priceFacture;

    public static ReservationDTO of(Reservation reservation, Double price){
        return new ReservationDTO(
            reservation.getRef(),
            reservation.getDate_arrive(),
            reservation.getDate_depart(),
            reservation.getStatus(),
            price
        );
    }
}
