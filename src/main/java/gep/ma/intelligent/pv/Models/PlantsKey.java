package gep.ma.intelligent.pv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyClass
public class PlantsKey implements Serializable {

    @PrimaryKeyColumn(name = "plantid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int plantId;

    @PrimaryKeyColumn(name = "datetime", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Timestamp datetime;
}
