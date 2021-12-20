package eksamen.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="political_parties")
public class PoliticalParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String abbreviation;

    private String name;

    @Column(nullable = true)
    private int totalVotes;

    @OneToMany(mappedBy = "politicalParty")
    @JsonBackReference
    private List<Candidate> candidates;

}
