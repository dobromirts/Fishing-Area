package restapi.fishigarea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.fishigarea.domain.entities.Fish;
import restapi.fishigarea.domain.entities.Fishpond;

import java.util.List;

@Repository
public interface FishRepository extends JpaRepository<Fish,String> {
    List<Fish> findAllByFishponds(Fishpond fishpond);
}
