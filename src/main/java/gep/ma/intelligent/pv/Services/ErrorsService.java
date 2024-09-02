package gep.ma.intelligent.pv.Services;

import org.springframework.stereotype.Service;
import java.util.List;
import gep.ma.intelligent.pv.Repos.ErrorsRepository;
import gep.ma.intelligent.pv.Models.Errors;
@Service
public class ErrorsService {

    private final ErrorsRepository errorsRepository;

    public ErrorsService(ErrorsRepository errorsRepository) {
        this.errorsRepository = errorsRepository;
    }

    public List<Errors> getAllErrors() {
        return errorsRepository.findAll();
    }
}
