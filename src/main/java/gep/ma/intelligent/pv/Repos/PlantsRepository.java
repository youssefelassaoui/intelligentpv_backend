package gep.ma.intelligent.pv.Repos;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import gep.ma.intelligent.pv.Models.Plants;
import gep.ma.intelligent.pv.Models.PlantsKey;

@Repository
public interface PlantsRepository extends CassandraRepository<Plants, PlantsKey> {
}
