package restapi.fishigarea.service;

import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.RegionServiceModel;

import java.util.List;

public interface RegionService {
    RegionServiceModel addRegion (RegionServiceModel regionServiceModel);

    RegionServiceModel getRegionByName(String name);

    void deleteRegion(String name);

    List<RegionServiceModel> findAllRegions();


}
