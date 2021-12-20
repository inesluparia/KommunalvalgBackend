package eksamen.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(columnDefinition = "integer default 0")
    private int totalVotes;

    //@JsonBackReference
    //@OneToMany(mappedBy = "politicalParty")
    //private List<Candidate> candidates;

    @JsonBackReference
    @OneToMany(mappedBy = "politicalParty")
    private List<Candidate> candidates;


    public PoliticalParty() {}

    public PoliticalParty(String abbreviation, String name, int totalVotes, List<Candidate> candidates) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.totalVotes = totalVotes;
        this.candidates = candidates;
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
