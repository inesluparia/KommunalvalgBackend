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

    //@Column(columnDefinition = "integer default 0")
    @Transient
    private int totalVotes;

    //@Column(columnDefinition = "integer default 0")
    @Transient
    private double votePercentage;

    @JsonBackReference
    @OneToMany(mappedBy = "politicalParty")
    private List<Candidate> candidates;


    public PoliticalParty() {}

    public PoliticalParty(long id, String abbreviation, String name, int totalVotes, double votePercetage, List<Candidate> candidates) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.totalVotes = totalVotes;
        this.votePercentage = votePercetage;
        this.candidates = candidates;
    }

    public PoliticalParty(long id, String abbreviation, String name) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        totalVotes = 0;
        votePercentage = 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getVotePercentage() {
        return votePercentage;
    }

    public void setVotePercentage(double votePercentage) {
        this.votePercentage = votePercentage;
    }

    public long getId() {
        return id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
