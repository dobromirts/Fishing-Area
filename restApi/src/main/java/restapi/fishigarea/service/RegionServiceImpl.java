package restapi.fishigarea.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.entities.Region;
import restapi.fishigarea.domain.models.service.RegionServiceModel;
import restapi.fishigarea.repository.RegionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService{
    private final RegionRepository regionRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository, ModelMapper modelMapper) {
        this.regionRepository = regionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RegionServiceModel addRegion(RegionServiceModel regionServiceModel) {
        return this.modelMapper.map(this.regionRepository.save(this.modelMapper.map(regionServiceModel, Region.class)),RegionServiceModel.class);
    }

    @Override
    public RegionServiceModel getRegionByName(String name) {
        return this.modelMapper.map(this.regionRepository.findRegionByName(name),RegionServiceModel.class);
    }

    @Override
    public void deleteRegion(String name) {
        Region region = this.regionRepository.findRegionByName(name);
        this.regionRepository.delete(region);
    }

    @Override
    public List<RegionServiceModel> findAllRegions() {
        return this.regionRepository.findAll().stream().map(r->this.modelMapper.map(r,RegionServiceModel.class)).collect(Collectors.toList());
    }
}
