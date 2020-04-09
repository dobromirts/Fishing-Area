package restapi.fishigarea.service;

import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.FishServiceModel;

import java.util.List;

public interface FishService {
    FishServiceModel addFish(List<String> fishponds,FishServiceModel fishServiceModel, MultipartFile file);
    List<FishServiceModel> getAllFishes();
    List<FishServiceModel> getAllFishesByFishpond(String id);
    FishServiceModel getFishById(String id);
}
