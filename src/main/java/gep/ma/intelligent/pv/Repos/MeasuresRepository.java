package gep.ma.intelligent.pv.Repos;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import gep.ma.intelligent.pv.Models.Measures;
import gep.ma.intelligent.pv.Models.MeasuresKey;

@Repository
public interface MeasuresRepository extends CassandraRepository<Measures, MeasuresKey> {
}
