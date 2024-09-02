package gep.ma.intelligent.pv.Repos;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import gep.ma.intelligent.pv.Models.Kpis;
import gep.ma.intelligent.pv.Models.KpisKey;

@Repository
public interface KpisRepository extends CassandraRepository<Kpis, KpisKey> {
}
