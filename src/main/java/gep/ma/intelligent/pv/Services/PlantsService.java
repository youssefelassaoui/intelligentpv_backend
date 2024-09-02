package gep.ma.intelligent.pv.Services;

import org.springframework.stereotype.Service;
import java.util.List;
import gep.ma.intelligent.pv.Repos.PlantsRepository;
import gep.ma.intelligent.pv.Models.Plants;

@Service
public class PlantsService {

    private final PlantsRepository plantsRepository;

    public PlantsService(PlantsRepository plantsRepository) {
        this.plantsRepository = plantsRepository;
    }

    public List<Plants> getAllPlants() {
        List<Plants> plants = plantsRepository.findAll();
        plants.forEach(plant -> {
            Double currentPower = plant.getCurrentPower() != null ? plant.getCurrentPower() : 0.0;
            // Handle other fields similarly if necessary
        });
        return plants;
    }

}
