package nl.gingerik.bzv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "bzv_votes")
public class Vote {

	private static final Logger log = Logger.getLogger(Vote.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "userid")
	@RestResource(exported = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "candidateid")
	@RestResource(exported = false)
	private Candidate candidate;

	@Column(name = "date_voted")
	private Date dateVoted;

	@ManyToOne
	@JoinColumn(name = "vote_typeid")
	@RestResource(exported = false)
	private VoteType voteType;

	@ManyToOne
	@JoinColumn(name = "vote_periodid")
	@RestResource(exported = false)
	private VotePeriod votePeriod;

	@Transient
	private int points;

	@PostLoad
	private void updatePoints() {
		if (!isValid()) {
			log.warn("Invalid vote: " + id);
			return;
		}
		Date dateElimination = candidate.getDateElimination();
		Date dateReference = votePeriod.getDateReference();
		boolean isEliminated = dateElimination != null
				&& dateElimination.before(dateReference)
				&& dateElimination.after(votePeriod.getDateStart());
		String type = voteType.getName();
		boolean votedElimination = VoteType.Name.BAD.name().equals(type);
		if (isEliminated == votedElimination) {
			points++;
		}
		//if (VoteType.Name.HEARTH.name().equals(type)) {
			EntityManagerFactory emf =  Persistence.createEntityManagerFactory("postgres");
		    EntityManager em = emf.createEntityManager();
		    Peasant peasant = em.find(Peasant.class, candidate.getPeasant().getId());
			if (peasant.getId() > 0) {
				points += 10*votePeriod.getVoteCount();
			}
		//}
	}

	private boolean isValid() {
		Date dateVoted = getDateVoted();
		return dateVoted.after(votePeriod.getDateStart())
				&& dateVoted.before(votePeriod.getDateEnd());
	}

	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public Date getDateVoted() {
		return dateVoted;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public VotePeriod getVotePeriod() {
		return votePeriod;
	}

	public int getPoints() {
		return points;
	}
}