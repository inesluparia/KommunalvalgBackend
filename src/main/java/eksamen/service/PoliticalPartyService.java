package eksamen.service;

import eksamen.model.Candidate;
import eksamen.model.PoliticalParty;
import eksamen.repository.CandidateRepo;
import eksamen.repository.PoliticalPartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        List<PoliticalParty> parties = politicalPartyRepo.findAll();

        //Sum of all votes so far
        int totalVotes = candidates.stream().map(Candidate::getVotes).reduce(0,(result, votes) -> result += votes);

        //get a Map of parties and respective totalVotes
        Map<PoliticalParty, Integer> partyVotesMap = candidates.stream().collect(Collectors
                        .toMap(Candidate::getPoliticalParty, Candidate::getVotes, (result, votes) -> result += votes));

        //set values totalVotes and percentage in parties fields
        for (PoliticalParty politicalParty : parties) {
            int partyVotes = partyVotesMap.get(politicalParty);
            politicalParty.setTotalVotes(partyVotes);
            double percentage = (partyVotes * 100.0) / totalVotes;
            double rounded = (double) Math.round(percentage * 100) / 100;
            politicalParty.setVotePercentage(rounded);
        }
        return parties;
    }
}
