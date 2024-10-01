package gep.ma.intelligent.pv.Repos;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import gep.ma.intelligent.pv.Models.Measures;
import gep.ma.intelligent.pv.Models.MeasuresKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


@Repository
public interface MeasuresRepository extends CassandraRepository<Measures, MeasuresKey> {
    Page<Measures> findAll(Pageable pageable);
}
