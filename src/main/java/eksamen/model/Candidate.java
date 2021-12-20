package eksamen.model;

import javax.persistence.*;

@Entity
@Table(name="candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    //@JoinColumn(name="political_party")
    private PoliticalParty politicalParty;

}
