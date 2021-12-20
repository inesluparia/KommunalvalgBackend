package eksamen.repository;

import eksamen.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepo extends JpaRepository<Candidate, Long> {

    List<Candidate> findAll();
    List<Candidate> findCandidateByPoliticalPartyId(Long id);
}
