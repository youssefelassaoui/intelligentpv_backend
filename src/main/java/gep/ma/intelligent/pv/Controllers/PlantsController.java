package gep.ma.intelligent.pv.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import gep.ma.intelligent.pv.Services.PlantsService;
import gep.ma.intelligent.pv.Models.Plants;

@RestController
@RequestMapping("/api/plants")
public class PlantsController {

    private final PlantsService plantsService;

    public PlantsController(PlantsService plantsService) {
        this.plantsService = plantsService;
    }

    @GetMapping
    public List<Plants> getAllPlants() {
        return plantsService.getAllPlants();
    }
}
