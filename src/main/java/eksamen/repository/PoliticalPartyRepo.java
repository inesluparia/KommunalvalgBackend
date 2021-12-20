package eksamen.repository;

import eksamen.model.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoliticalPartyRepo extends JpaRepository<PoliticalParty, Long> {

List<PoliticalParty> findAll();

}
