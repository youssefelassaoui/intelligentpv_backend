package gep.ma.intelligent.pv.Repos;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import gep.ma.intelligent.pv.Models.Plants;
import gep.ma.intelligent.pv.Models.PlantsKey;
import org.springframework.data.cassandra.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.time.Instant;
import java.sql.Timestamp;
import gep.ma.intelligent.pv.DTO.PlantEnergyData;
import java.util.Map;

@Repository
public interface PlantsRepository extends CassandraRepository<Plants, PlantsKey> {
    // Correct CQL Query

//    @Query("SELECT SUM(dayEnergy) FROM plants WHERE plantId = ?0 AND datetime >= ?1 AND datetime <= ?2 ALLOW FILTERING")
//    Double getTotalEnergyBetweenDates(Integer plantId, Timestamp startDate, Timestamp endDate);
@Query("SELECT * FROM plants WHERE plantid = ?0 AND datetime >= ?1 AND datetime <= ?2 ALLOW FILTERING")
List<Plants> getEnergyBetweenDates(int plantId, Instant startDate, Instant endDate);




    // Query to get total energy for each day between two dates
    @Query("SELECT SUM(p.dayEnergy) FROM Plants p WHERE p.date >= :startDate AND p.date <= :endDate GROUP BY p.date")
    List<Double> getTotalEnergyPerDayBetweenDates(LocalDate startDate, LocalDate endDate);




}
