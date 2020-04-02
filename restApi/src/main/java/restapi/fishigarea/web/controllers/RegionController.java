package restapi.fishigarea.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.fishigarea.domain.models.service.RegionServiceModel;
import restapi.fishigarea.service.RegionService;
import restapi.fishigarea.web.models.request.region.RegionModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/regions")
public class RegionController {
    private RegionService regionService;
    private ModelMapper modelMapper;

    @Autowired
    public RegionController(RegionService regionService, ModelMapper modelMapper) {
        this.regionService = regionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity addRegion(@RequestBody RegionModel model){
        RegionServiceModel region=this.regionService.addRegion(this.modelMapper.map(model, RegionServiceModel.class));
        return ResponseEntity.ok().body(region);
    }

    @GetMapping("/all")
    public List<RegionModel> getAllRegions(){
        return this.regionService.findAllRegions().stream().map(r->this.modelMapper.map(r,RegionModel.class)).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public RegionModel getRegionByName(@PathVariable("name") String name){
        return this.modelMapper.map(this.regionService.getRegionByName(name),RegionModel.class);
    }

    @DeleteMapping("/delete/{name}")
    public boolean deleteRegion(@PathVariable("name") String name){
        try {
            this.regionService.deleteRegion(name);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
