package gep.ma.intelligent.pv.Repos;

import gep.ma.intelligent.pv.Models.DevicesByPlant;
import gep.ma.intelligent.pv.Models.DevicesByPlantKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.cassandra.repository.Query;

@Repository
public interface DevicesByPlantRepository extends CassandraRepository<DevicesByPlant, Long> {

    // Query to find devices by plantId and list of deviceIds
    @Query("SELECT * FROM devices_by_plant WHERE plantid = ?0 AND deviceid IN ?1")
    List<DevicesByPlant> findByKeyPlantIdAndKeyDeviceIdIn(int plantId, List<Integer> deviceIds);
}

