package eksamen.controller;

import eksamen.ResourceNotFoundException;
import eksamen.model.Candidate;
import eksamen.model.PoliticalParty;
import eksamen.repository.CandidateRepo;
import eksamen.repository.PoliticalPartyRepo;
import eksamen.service.PoliticalPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
public class RestController {

    @Autowired
    CandidateRepo candidateRepo;

    @Autowired
    PoliticalPartyService politicalPartyService;

    @Autowired
    PoliticalPartyRepo partyRepo;

    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates(@PathParam("party") Optional<Long> party) {
        if (party.isPresent()) {
            List<Candidate> partyCandidates =candidateRepo.findCandidateByPoliticalPartyId(party.get());
            return ResponseEntity.ok().body(partyCandidates);
        } else {
            List<Candidate> candidates = candidateRepo.findAll();
            return ResponseEntity.ok().body(candidates);}
    }

    @GetMapping("/candidates/{id}")
    public ResponseEntity<Candidate> findById(@PathVariable Long id){
        Optional<Candidate> candidate = candidateRepo.findById(id);
        if (!candidate.isPresent())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(candidateRepo.findById(id).get());
    }


    @GetMapping("/parties")
    public ResponseEntity<List<PoliticalParty>> getAllParties(){
        List<PoliticalParty> parties = politicalPartyService.GetParties();
        return ResponseEntity.ok().body(parties);
    }

    @DeleteMapping("/candidates/{id}")
        ResponseEntity<?> deleteOne(@PathVariable("id") Long id) {
        Optional<Candidate> candidate = candidateRepo.findById(id);
        if (!candidate.isPresent())
            return ResponseEntity.notFound().build();
        else {
            candidateRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }


    @CrossOrigin(value = "*", exposedHeaders = "Location")
    @PostMapping(value = "/candidates/{partyId}", consumes = "application/json")
    public ResponseEntity<Candidate> AddCandidate(@RequestBody Candidate candidate, @PathVariable Long partyId) throws Exception {
        PoliticalParty party = partyRepo.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException ("Party not found with id: " + partyId));
        candidate.setPoliticalParty(party);
        candidateRepo.save(candidate);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/candidates/" + candidate.getId()).build();
    }

    @CrossOrigin(value = "*", exposedHeaders = "Location")
    @PutMapping("/candidates/{id}")
    public ResponseEntity<Candidate> updateCandidate (@PathVariable Long id, @RequestBody Candidate candidate) throws Exception {
       Candidate candToUpdate = candidateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));
       candToUpdate.setName(candidate.getName());
       candidateRepo.save(candToUpdate);

       return ResponseEntity.status(HttpStatus.OK)
                .header("Location", "/candidates/" + id).build();
    }

}

