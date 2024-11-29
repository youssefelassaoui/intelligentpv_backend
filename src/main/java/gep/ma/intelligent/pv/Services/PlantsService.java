package gep.ma.intelligent.pv.Services;

import gep.ma.intelligent.pv.Models.Plants;
import gep.ma.intelligent.pv.Repos.PlantsRepository;
import org.springframework.stereotype.Service;
import gep.ma.intelligent.pv.DTO.EnergyDataDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.util.*;
import gep.ma.intelligent.pv.DTO.PlantEnergyData;



@Service
public class PlantsService {

    private final PlantsRepository plantsRepository;

    public PlantsService(PlantsRepository plantsRepository) {
        this.plantsRepository = plantsRepository;
    }

    public List<Plants> getAllPlants() {
        List<Plants> plants = plantsRepository.findAll();
        plants.forEach(plant -> {
            Double currentPower = plant.getCurrentPower() != null ? plant.getCurrentPower() : 0.0;
            // Handle other fields similarly if necessary
        });
        return plants;
    }
    public EnergyDataDTO calculateEnergyData(int plantId) {
        // Define the timezone
        ZoneId zoneId = ZoneId.of("UTC");

        // Get the current time
        Instant now = Instant.now();

        // Calculate the start of the current week (Monday)
        Instant startOfWeek = now.atZone(zoneId).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toInstant();

        // Calculate the same day of the current week (so if today is Thursday, we stop at Thursday)
        Instant endOfPartialWeek = now;

        // Calculate the start of the previous week (Monday of the previous week)
        Instant startOfPreviousWeek = startOfWeek.minus(7, ChronoUnit.DAYS);

        // Calculate the same day of the previous week (so if today is Thursday, we stop at Thursday of the previous week)
        Instant endOfPreviousWeek = startOfPreviousWeek.plus(ChronoUnit.DAYS.between(startOfWeek, endOfPartialWeek), ChronoUnit.DAYS);

        // Fetch data for the current and previous weeks up to the same day
        List<Plants> currentWeekData = plantsRepository.getEnergyBetweenDates(plantId, startOfWeek, endOfPartialWeek);
        List<Plants> previousWeekData = plantsRepository.getEnergyBetweenDates(plantId, startOfPreviousWeek, endOfPreviousWeek);

        // Group and sum by date
        Map<LocalDate, Double> currentWeekEnergyByDay = groupByDateAndSumEnergy(currentWeekData);
        Map<LocalDate, Double> previousWeekEnergyByDay = groupByDateAndSumEnergy(previousWeekData);

        // Ensure all days up to today are present for both weeks
        for (LocalDate date = startOfWeek.atZone(zoneId).toLocalDate(); !date.isAfter(endOfPartialWeek.atZone(zoneId).toLocalDate()); date = date.plusDays(1)) {
            currentWeekEnergyByDay.putIfAbsent(date, 0.0);
        }
        for (LocalDate date = startOfPreviousWeek.atZone(zoneId).toLocalDate(); !date.isAfter(endOfPreviousWeek.atZone(zoneId).toLocalDate()); date = date.plusDays(1)) {
            previousWeekEnergyByDay.putIfAbsent(date, 0.0);
        }

        // Calculate total energy for the current partial week and the same period in the previous week
        double totalCurrentWeekEnergy = currentWeekEnergyByDay.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalPreviousWeekEnergy = previousWeekEnergyByDay.values().stream().mapToDouble(Double::doubleValue).sum();

        // Calculate percentage change
        double percentageChange = (totalPreviousWeekEnergy == 0) ? 100.0 : ((totalCurrentWeekEnergy - totalPreviousWeekEnergy) / totalPreviousWeekEnergy) * 100;

        // Prepare response
        EnergyDataDTO response = new EnergyDataDTO();
        response.setCurrentWeekEnergy(totalCurrentWeekEnergy);
        response.setPreviousWeekEnergy(totalPreviousWeekEnergy);
        response.setPercentageChange(percentageChange);
        response.setCurrentWeekDailyData(currentWeekEnergyByDay);
        response.setPreviousWeekDailyData(previousWeekEnergyByDay);

        return response;
    }


    public Map<LocalDate, Double> groupByDateAndSumEnergy(List<Plants> plants) {
        return plants.stream()
                .collect(Collectors.groupingBy(
                        plant -> plant.getKey().getDatetime().toInstant().atZone(ZoneId.of("UTC")).toLocalDate(),  // Group by LocalDate
                        Collectors.summingDouble(Plants::getDayEnergy)  // Sum dayEnergy
                ));
    }

}


