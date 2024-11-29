package gep.ma.intelligent.pv.Repos;

import gep.ma.intelligent.pv.Models.Measures;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.data.cassandra.repository.Query;
import gep.ma.intelligent.pv.Models.MeasuresKey;
import org.springframework.data.domain.Page;
import java.time.Instant;

@Repository
public interface MeasuresRepository extends CassandraRepository<Measures, Long> {

    // Query to find measures by plantId, variableType, variable, and datetime range with pagination
    @Query("SELECT * FROM measures WHERE plantid = ?0 AND variabletype = ?1 AND variable = ?2 AND datetime >= ?3 AND datetime <= ?4 ALLOW FILTERING")
    Slice<Measures> findByKeyPlantIdAndVariableTypeAndVariableAndDatetimeBetween(
            int plantId,
            String variableType,
            String variable,
            Instant startDate,
            Instant endDate,
            Pageable pageable
    );


}
