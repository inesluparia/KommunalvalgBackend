package eksamen.repository;

import eksamen.model.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticalPartyRepo extends JpaRepository<PoliticalParty, String> {

}
