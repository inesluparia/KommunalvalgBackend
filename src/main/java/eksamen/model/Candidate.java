package eksamen.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "integer default 120")
    private int votes;

    private String name;

//    @JsonManagedReference
//    @ManyToOne
//    @JoinColumn(name="political_party_id")
//    private PoliticalParty politicalParty;

    @ManyToOne
    @JoinColumn(name = "political_party_id")
    private PoliticalParty politicalParty;

    public Candidate(){}

    public Candidate(String name) {
        this.name = name;
        votes = 0;
    }

    public Candidate(String name, PoliticalParty politicalParty) {
        this.name = name;
        this.politicalParty = politicalParty;
        votes = 0;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PoliticalParty getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(PoliticalParty politicalParty) {
        this.politicalParty = politicalParty;
    }
}
