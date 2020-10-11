package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the goal_track_card database table.
 * 
 */
@Entity
@Table(name="goal_track_card")
@NamedQuery(name="GoalTrackCard.findAll", query="SELECT g FROM GoalTrackCard g")
public class GoalTrackCard extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String description;
	private String title;
	private ClientAccount clientAccount;
	private OrgGoalDefinition orgGoalDefinition;
	private OrgGoalDomain orgGoalDomain;
	private List<TrackedItemToCard> trackedItemToCards;

	public GoalTrackCard() {
	}


	@Id
	@SequenceGenerator(name="GOAL_TRACK_CARD_ID_GENERATOR", sequenceName="SEQ_GOAL_TRACK_CARD_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOAL_TRACK_CARD_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(nullable=false, length=1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(nullable=false, length=255)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//bi-directional many-to-one association to ClientAccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="owner_client_account_id", nullable=false)
	public ClientAccount getClientAccount() {
		return this.clientAccount;
	}

	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}


	//bi-directional many-to-one association to OrgGoalDefinition
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="base_org_goal_definition_id", nullable=false)
	public OrgGoalDefinition getOrgGoalDefinition() {
		return this.orgGoalDefinition;
	}

	public void setOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		this.orgGoalDefinition = orgGoalDefinition;
	}


	//bi-directional many-to-one association to OrgGoalDomain
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="org_goal_domain_id", nullable=false)
	public OrgGoalDomain getOrgGoalDomain() {
		return this.orgGoalDomain;
	}

	public void setOrgGoalDomain(OrgGoalDomain orgGoalDomain) {
		this.orgGoalDomain = orgGoalDomain;
	}


	//bi-directional many-to-one association to TrackedItemToCard
	@OneToMany(mappedBy="goalTrackCard")
	public List<TrackedItemToCard> getTrackedItemToCards() {
		return this.trackedItemToCards;
	}

	public void setTrackedItemToCards(List<TrackedItemToCard> trackedItemToCards) {
		this.trackedItemToCards = trackedItemToCards;
	}

	public TrackedItemToCard addTrackedItemToCard(TrackedItemToCard trackedItemToCard) {
		getTrackedItemToCards().add(trackedItemToCard);
		trackedItemToCard.setGoalTrackCard(this);

		return trackedItemToCard;
	}

	public TrackedItemToCard removeTrackedItemToCard(TrackedItemToCard trackedItemToCard) {
		getTrackedItemToCards().remove(trackedItemToCard);
		trackedItemToCard.setGoalTrackCard(null);

		return trackedItemToCard;
	}

}