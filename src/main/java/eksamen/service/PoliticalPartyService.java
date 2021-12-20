package eksamen.service;

import eksamen.repository.PoliticalPartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliticalPartyService {

    @Autowired
    PoliticalPartyRepo politicalPartyRepo;


}
