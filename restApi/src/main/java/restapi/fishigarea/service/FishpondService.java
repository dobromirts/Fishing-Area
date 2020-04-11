package restapi.fishigarea.service;

import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.FishpondServiceModel;

import java.util.List;

public interface FishpondService {
    FishpondServiceModel addFishpond(String regionName,FishpondServiceModel fishpondServiceModel, MultipartFile file);

    List<FishpondServiceModel> getAllFishponds();

    List<FishpondServiceModel> getAllFishpondsByRegion(String name);

    FishpondServiceModel getFishpondById(String id);

    boolean delete (String id);

    FishpondServiceModel getFishPondsByName(String name);
}
