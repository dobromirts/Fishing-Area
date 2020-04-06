package restapi.fishigarea.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.FishpondServiceModel;
import restapi.fishigarea.service.FishpondService;
import restapi.fishigarea.web.models.request.fishpond.FishpondAddModel;

@RestController
@RequestMapping("api/fishpond")
public class FishpondController {
    private final FishpondService fishpondService;
    private final ModelMapper modelMapper;

    @Autowired
    public FishpondController(FishpondService fishpondService, ModelMapper modelMapper) {
        this.fishpondService = fishpondService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public FishpondAddModel addFishpond(@RequestPart("fishpondAddModel") FishpondAddModel model, @RequestPart("file") MultipartFile file){
        FishpondServiceModel serviceModel=this.modelMapper.map(model,FishpondServiceModel.class);
        return this.modelMapper.map(this.fishpondService.addFishpond(model.getRegionName(),serviceModel,file),FishpondAddModel.class);
    }
}
