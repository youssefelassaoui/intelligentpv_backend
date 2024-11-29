package gep.ma.intelligent.pv.DTO;
import java.time.LocalDate;
import java.util.Map;
import lombok.Data;

import java.util.List;

@Data
public class EnergyDataDTO {
    private double currentWeekEnergy;
    private double previousWeekEnergy;
    private double percentageChange;
    private Map<LocalDate, Double> currentWeekDailyData;
    private Map<LocalDate, Double> previousWeekDailyData;
}
