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
public class ErrorsKey implements Serializable {

    @PrimaryKeyColumn(name = "type", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String type;

    @PrimaryKeyColumn(name = "pvphase", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private int pvPhase;

    @PrimaryKeyColumn(name = "pvinverter", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private String pvInverter;

    @PrimaryKeyColumn(name = "pvstring", ordinal = 3, type = PrimaryKeyType.PARTITIONED)
    private String pvString;
}
