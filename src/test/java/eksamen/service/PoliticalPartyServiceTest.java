package eksamen.service;
import eksamen.model.Candidate;
import eksamen.model.PoliticalParty;
import eksamen.repository.CandidateRepo;
import eksamen.repository.PoliticalPartyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class PoliticalPartyServiceTest {

    @Mock
    PoliticalPartyRepo partyRepo;

    @Mock
    CandidateRepo candidateRepo;

    @InjectMocks
    PoliticalPartyService service;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void Test1() {

        List<PoliticalParty> dummyParties = new ArrayList<>();
        PoliticalParty party1 = new PoliticalParty(1,"1", "Party 1");
        dummyParties.add(party1);
        PoliticalParty party2 = new PoliticalParty(2,"2", "Party 2");
        dummyParties.add(party2);

        List<Candidate> dummyCandidates = new ArrayList<>();
        dummyCandidates.add(new Candidate("Lars", party1, 50));
        dummyCandidates.add(new Candidate("Helle", party1, 50));
        dummyCandidates.add(new Candidate("Mette", party1, 50));
        dummyCandidates.add(new Candidate("Pia", party2, 50));
        dummyCandidates.add(new Candidate("Klaus", party2, 50));

        when(partyRepo.findAll()).thenReturn(dummyParties);
        when(candidateRepo.findAll()).thenReturn(dummyCandidates);

        int totalVotes = dummyCandidates.stream().map(Candidate::getVotes).reduce(0,(result, votes) -> result += votes);
        assertEquals(250,totalVotes);

        List<PoliticalParty> result = service.GetParties();
        assertEquals(2, result.size());
        assertEquals(result.get(0).getTotalVotes(),150);

    }

}
