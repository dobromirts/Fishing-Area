package restapi.fishigarea.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.FishServiceModel;
import restapi.fishigarea.service.FishService;
import restapi.fishigarea.web.models.request.fish.FishAddModel;
import restapi.fishigarea.web.models.response.fish.FishResponseModel;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/fish")
public class FishController {
    private final FishService fishService;
    private final ModelMapper modelMapper;

    @Autowired
    public FishController(FishService fishService, ModelMapper modelMapper) {
        this.fishService = fishService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/add")
    public FishAddModel addFishpond(@RequestPart("fishAddModel") FishAddModel model, @RequestPart("file") MultipartFile file){
        FishServiceModel serviceModel=this.modelMapper.map(model,FishServiceModel.class);
        return this.modelMapper.map(this.fishService.addFish(model.getFishponds(),serviceModel,file),FishAddModel.class);
    }

    @GetMapping("/all/{id}")
    public List<FishResponseModel> getAllForFishpond(@PathVariable String id){
        return this.fishService.getAllFishesByFishpond(id).stream().map(f->this.modelMapper.map(f,FishResponseModel.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FishResponseModel getFishpondById(@PathVariable String id){
        return this.modelMapper.map(this.fishService.getFishById(id),FishResponseModel.class);
    }


}
