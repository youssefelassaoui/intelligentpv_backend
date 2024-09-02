package gep.ma.intelligent.pv.Repos;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import gep.ma.intelligent.pv.Models.DevicesByPlant;
import gep.ma.intelligent.pv.Models.DevicesByPlantKey;
@Repository
public interface DevicesByPlantRepository extends CassandraRepository<DevicesByPlant, DevicesByPlantKey> {
}
