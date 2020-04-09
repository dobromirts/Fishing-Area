package restapi.fishigarea.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.entities.Fishpond;
import restapi.fishigarea.domain.entities.Region;
import restapi.fishigarea.domain.models.service.FishpondServiceModel;
import restapi.fishigarea.repository.FishpondRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishpondServiceImpl implements FishpondService {
    private final FishpondRepository fishpondRepository;
    private final ModelMapper modelMapper;
    private  final CloudinaryService cloudinaryService;
    private final RegionService regionService;

    @Autowired
    public FishpondServiceImpl(FishpondRepository fishpondRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService, RegionService regionService) {
        this.fishpondRepository = fishpondRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.regionService = regionService;
    }

    @Override
    public FishpondServiceModel addFishpond(String regionName,FishpondServiceModel fishpondServiceModel, MultipartFile file) {
        Fishpond fishpond=this.modelMapper.map(fishpondServiceModel,Fishpond.class);
        Region region=this.modelMapper.map(this.regionService.getRegionByName(regionName), Region.class);
        fishpond.setRegion(region);
        try {
            fishpond.setImageUrl(this.cloudinaryService.uploadImg(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.modelMapper.map(this.fishpondRepository.saveAndFlush(fishpond),FishpondServiceModel.class);
    }

    @Override
    public List<FishpondServiceModel> getAllFishponds() {
        return this.fishpondRepository.findAll().stream().map(f->this.modelMapper.map(f,FishpondServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<FishpondServiceModel> getAllFishpondsByRegion(String name) {
        Region region=this.modelMapper.map(this.regionService.getRegionByName(name),Region.class);
        return this.fishpondRepository.findAllByRegion(region).stream().map(f->this.modelMapper.map(f,FishpondServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public FishpondServiceModel getFishpondById(String id) {
        Fishpond fishpond=this.fishpondRepository.findById(id).orElseThrow();

        return this.modelMapper.map(fishpond,FishpondServiceModel.class);
    }

    @Override
    public boolean delete(String id) {
        try {
            Fishpond fishpond=this.fishpondRepository.findById(id).orElseThrow();
            this.fishpondRepository.delete(fishpond);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
