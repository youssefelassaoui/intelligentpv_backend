package gep.ma.intelligent.pv.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("devices_by_plant")
public class DevicesByPlant {

    @PrimaryKey
    private DevicesByPlantKey key;

    @Column("devicename")
    private String deviceName;

    @Column("devicetype")
    private String deviceType;

    @Column("plantname")
    private String plantName;
}

