package restapi.fishigarea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.fishigarea.domain.entities.Fishpond;
import restapi.fishigarea.domain.entities.Region;

import java.util.List;

@Repository
public interface FishpondRepository extends JpaRepository<Fishpond,String> {
    Fishpond findFishpondByName(String name);
    List<Fishpond> findAllByRegion(Region region);
}
