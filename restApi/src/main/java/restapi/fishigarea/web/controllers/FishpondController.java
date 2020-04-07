package restapi.fishigarea.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.FishpondServiceModel;
import restapi.fishigarea.service.FishpondService;
import restapi.fishigarea.web.models.request.fishpond.FishpondAddModel;
import restapi.fishigarea.web.models.response.fishpond.FishpondResponseModel;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/all/{name}")
    public List<FishpondResponseModel> getAllFishpondByRegion(@PathVariable String name) {
        return this.fishpondService.getAllFishpondsByRegion(name).stream().map(f->this.modelMapper.map(f,FishpondResponseModel.class)).collect(Collectors.toList());
    }


}
