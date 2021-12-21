package eksamen.service;

import eksamen.model.Candidate;
import eksamen.model.PoliticalParty;
import eksamen.repository.CandidateRepo;
import eksamen.repository.PoliticalPartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PoliticalPartyService {

    @Autowired
    PoliticalPartyRepo politicalPartyRepo;

    @Autowired
    CandidateRepo candidateRepo;

    public List<PoliticalParty> GetParties() {

        List<Candidate> candidates = candidateRepo.findAll();

        Map<Long, Integer> partyVotesMap = new HashMap<>();
        for (Candidate c : candidates){
            Long partyId = c.getPoliticalParty().getId();
            partyVotesMap.putIfAbsent(partyId, 0);
            partyVotesMap.put(partyId, partyVotesMap.get(partyId)+ c.getVotes());
        }

        List<PoliticalParty> parties = politicalPartyRepo.findAll();
        for (int i = 0; i < parties.size(); i ++){
            int partyTotal =  partyVotesMap.get(parties.get(i).getId());
            parties.get(i).setTotalVotes(partyTotal);
        }

        return parties;

        //NOT
//        List<PoliticalParty> updatedParties = parties.stream()
//                .map( p -> {
//                    int partyTotal =  partyVotesMap.get(p.getId());
//                    p.setTotalVotes(partyTotal);
//                    return p;
//                }).collect(Collectors.toList());
    }
}

