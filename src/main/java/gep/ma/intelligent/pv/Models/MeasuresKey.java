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
public class MeasuresKey implements Serializable {

    @PrimaryKeyColumn(name = "plantid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int plantId;

    @PrimaryKeyColumn(name = "deviceid", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private int deviceId;

    @PrimaryKeyColumn(name = "datetime", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private Timestamp datetime;

    @PrimaryKeyColumn(name = "variable", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    private String variable;

    @PrimaryKeyColumn(name = "variabletype", ordinal = 4, type = PrimaryKeyType.CLUSTERED)
    private String variableType;
}
