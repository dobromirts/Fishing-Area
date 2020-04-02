package restapi.fishigarea.service;

import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.FishpondServiceModel;

import java.util.List;

public interface FishpondService {
    FishpondServiceModel addFishpond(String regionName,FishpondServiceModel fishpondServiceModel, MultipartFile file);

    List<FishpondServiceModel> getAllFishponds();

    FishpondServiceModel getFishpondByName(String name);

    boolean delete (String name);
}
