package gep.ma.intelligent.pv.Controllers;
import gep.ma.intelligent.pv.Services.PlantsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import gep.ma.intelligent.pv.Models.Plants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import gep.ma.intelligent.pv.DTO.EnergyDataDTO;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/energyData/{plantId}")
    public ResponseEntity<EnergyDataDTO> getEnergyData(@PathVariable int plantId) {
        EnergyDataDTO energyData = plantsService.calculateEnergyData(plantId);
        return ResponseEntity.ok(energyData);
    }

    // Endpoint to get the energy for each day in the last week (for sparklines)

}
