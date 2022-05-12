package be.technifutur.reservation.models.forms;

import lombok.Data;

import java.time.LocalDate;

import be.technifutur.reservation.models.entities.Reservation;

@Data
public class ReservForm {

    private LocalDate arrive;
    private LocalDate depart;

    public Reservation map(){
        return new Reservation(arrive, depart);
    }

}
