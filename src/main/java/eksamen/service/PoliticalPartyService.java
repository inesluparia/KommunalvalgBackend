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

        Map<Long, Integer> partyVotesMap = getVotesMap(candidates);

        int votesCount = 0;

        List<PoliticalParty> parties = politicalPartyRepo.findAll();

        //count votes and set votes total
        for (int i = 0; i < parties.size(); i ++){
            int partyTotal =  partyVotesMap.get(parties.get(i).getId());
            votesCount += partyTotal;
            parties.get(i).setTotalVotes(partyTotal);
        }

        //calculate and set percentage
        for (int i = 0; i < parties.size(); i ++){
            double partyVotes = parties.get(i).getTotalVotes();
            double percentage =  (partyVotes * 100) / votesCount;
            double rounded = (double) Math.round(percentage * 100) / 100;
            parties.get(i).setVotePercentage(rounded);
        }

            return parties;

//for next time with more time...
//        List<PoliticalParty> updatedParties = parties.stream()
//                .map( p -> {
//                    int partyTotal =  partyVotesMap.get(p.getId());
//                    p.setTotalVotes(partyTotal);
//                    return p;
//                }).collect(Collectors.toList());
    }

    private Map<Long, Integer> getVotesMap(List<Candidate> candidates) {
        Map<Long, Integer> partyVotesMap = new HashMap<>();
        for (Candidate c : candidates){
            Long partyId = c.getPoliticalParty().getId();
            partyVotesMap.putIfAbsent(partyId, 0);
            partyVotesMap.put(partyId, partyVotesMap.get(partyId)+ c.getVotes());
        }
        return partyVotesMap;
    }
}

