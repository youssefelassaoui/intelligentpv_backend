package gep.ma.intelligent.pv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyClass
public class KpisKey implements Serializable {

    @PrimaryKeyColumn(name = "kpiname", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String kpiName;

    @PrimaryKeyColumn(name = "pvphase", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private int pvPhase;

    @PrimaryKeyColumn(name = "value", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private double value;
}
