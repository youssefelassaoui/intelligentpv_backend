package gep.ma.intelligent.pv.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlantEnergyData {
    private LocalDateTime datetime;
    private Double dayEnergy;

}
