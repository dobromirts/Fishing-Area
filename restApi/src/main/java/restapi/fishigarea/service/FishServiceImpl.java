package restapi.fishigarea.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.entities.Fish;
import restapi.fishigarea.domain.entities.Fishpond;
import restapi.fishigarea.domain.models.service.FishServiceModel;
import restapi.fishigarea.domain.models.service.FishpondServiceModel;
import restapi.fishigarea.repository.FishRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishServiceImpl implements FishService{
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final FishpondService fishpondService;

    @Autowired
    public FishServiceImpl(FishRepository fishRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService, FishpondService fishpondService) {
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.fishpondService = fishpondService;
    }

    @Override
    public FishServiceModel addFish(List<String> fishponds, FishServiceModel fishServiceModel, MultipartFile file) {
        Fish fish=this.modelMapper.map(fishServiceModel,Fish.class);
        List<Fishpond> ponds=new ArrayList<>();
        fishponds.forEach(f->{
            ponds.add(this.modelMapper.map(this.fishpondService.getFishpondById(f),Fishpond.class));
        });
        fish.setFishponds(ponds);
        try {
            fish.setImageUrl(this.cloudinaryService.uploadImg(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.modelMapper.map(this.fishRepository.save(fish),FishServiceModel.class);
    }

    @Override
    public List<FishServiceModel> getAllFishes() {
        return this.fishRepository.findAll().stream().map(f->this.modelMapper.map(f,FishServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<FishServiceModel> getAllFishesByFishpond(String id) {
        Fishpond fishpond=this.modelMapper.map(this.fishpondService.getFishpondById(id),Fishpond.class);
        List<Fish> fishes=this.fishRepository.findAllByFishponds(fishpond);
        return fishes.stream().map(f->this.modelMapper.map(f,FishServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public FishServiceModel getFishById(String id) {
        Fish fish= this.fishRepository.findById(id).orElseThrow();
        return this.modelMapper.map(fish,FishServiceModel.class);
    }
}
