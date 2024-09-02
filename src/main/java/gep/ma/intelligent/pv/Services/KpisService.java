package gep.ma.intelligent.pv.Services;

import org.springframework.stereotype.Service;
import java.util.List;
import gep.ma.intelligent.pv.Repos.KpisRepository;
import gep.ma.intelligent.pv.Models.Kpis;
@Service
public class KpisService {

    private final KpisRepository kpisRepository;

    public KpisService(KpisRepository kpisRepository) {
        this.kpisRepository = kpisRepository;
    }

    public List<Kpis> getAllKpis() {
        return kpisRepository.findAll();
    }
}
