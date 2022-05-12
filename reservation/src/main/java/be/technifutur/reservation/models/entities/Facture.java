package be.technifutur.reservation.models.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {
    private double prix;
    private UUID reserv_ref;
}
