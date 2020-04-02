package restapi.fishigarea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.fishigarea.domain.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,String> {
    Region findRegionByName(String name);
}
