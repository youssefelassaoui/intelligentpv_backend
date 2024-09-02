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
public class DevicesByPlantKey implements Serializable {

    @PrimaryKeyColumn(name = "deviceid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int deviceId;

    @PrimaryKeyColumn(name = "plantid", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private int plantId;
}
