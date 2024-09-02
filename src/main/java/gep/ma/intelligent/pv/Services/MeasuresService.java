package gep.ma.intelligent.pv.Services;

import org.springframework.stereotype.Service;
import java.util.List;
import gep.ma.intelligent.pv.Repos.MeasuresRepository;
import gep.ma.intelligent.pv.Models.Measures;
@Service
public class MeasuresService {

    private final MeasuresRepository measuresRepository;

    public MeasuresService(MeasuresRepository measuresRepository) {
        this.measuresRepository = measuresRepository;
    }

    public List<Measures> getAllMeasures() {
        return measuresRepository.findAll();
    }
}
