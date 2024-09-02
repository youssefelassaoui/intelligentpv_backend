package gep.ma.intelligent.pv.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import gep.ma.intelligent.pv.Services.ErrorsService;
import gep.ma.intelligent.pv.Models.Errors;

@RestController
@RequestMapping("/api/errors")
public class ErrorsController {

    private final ErrorsService errorsService;

    public ErrorsController(ErrorsService errorsService) {
        this.errorsService = errorsService;
    }

    @GetMapping
    public List<Errors> getAllErrors() {
        return errorsService.getAllErrors();
    }
}
