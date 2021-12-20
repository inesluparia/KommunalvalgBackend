package eksamen.controller;

import eksamen.model.Candidate;
import eksamen.model.PoliticalParty;
import eksamen.repository.CandidateRepo;
import eksamen.repository.PoliticalPartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RestController {

    @Autowired
    CandidateRepo candidateRepo;

    @Autowired
    PoliticalPartyRepo partyRepo;

    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates(){
       List<Candidate> candidates = candidateRepo.findAll();
      return ResponseEntity.ok().body(candidates);
    }

    @GetMapping("/parties")
    public ResponseEntity<List<PoliticalParty>> getAllParties(){
        List<PoliticalParty> parties = partyRepo.findAll();
        return ResponseEntity.ok().body(parties);
    }

}
