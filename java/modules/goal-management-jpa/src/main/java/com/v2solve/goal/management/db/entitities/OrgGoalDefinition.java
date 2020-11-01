package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the org_goal_definition database table.
 * 
 */
@Entity
@Table(name="org_goal_definition")
@NamedQuery(name="OrgGoalDefinition.findAll", query="SELECT o FROM OrgGoalDefinition o")
public class OrgGoalDefinition extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String description;
	private String goalDefinitionState;
	private double goalWeight;
	private String title;
	private List<GoalTrackCard> goalTrackCards;
	private List<GoalValueType> goalValueTypes;
	private ClientAccount clientAccount;
	private OrgGoalDefinition orgGoalDefinition;
	private List<OrgGoalDefinition> orgGoalDefinitions;
	private OrgGoalDomain orgGoalDomain;
	private List<TrackedItemGoal> trackedItemGoals;

	public OrgGoalDefinition() {
	}


	@Id
	@SequenceGenerator(name="ORG_GOAL_DEFINITION_ID_GENERATOR", sequenceName="SEQ_ORG_GOAL_DEFINITION_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORG_GOAL_DEFINITION_ID_GENERATOR")
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


	@Column(name="goal_definition_state", nullable=false, length=50)
	public String getGoalDefinitionState() {
		return this.goalDefinitionState;
	}

	public void setGoalDefinitionState(String goalDefinitionState) {
		this.goalDefinitionState = goalDefinitionState;
	}


	@Column(name="goal_weight")
	public double getGoalWeight() {
		return this.goalWeight;
	}

	public void setGoalWeight(double goalWeight) {
		this.goalWeight = goalWeight;
	}


	@Column(nullable=false, length=255)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//bi-directional many-to-one association to GoalTrackCard
	@OneToMany(mappedBy="orgGoalDefinition")
	public List<GoalTrackCard> getGoalTrackCards() {
		return this.goalTrackCards;
	}

	public void setGoalTrackCards(List<GoalTrackCard> goalTrackCards) {
		this.goalTrackCards = goalTrackCards;
	}

	public GoalTrackCard addGoalTrackCard(GoalTrackCard goalTrackCard) {
		getGoalTrackCards().add(goalTrackCard);
		goalTrackCard.setOrgGoalDefinition(this);

		return goalTrackCard;
	}

	public GoalTrackCard removeGoalTrackCard(GoalTrackCard goalTrackCard) {
		getGoalTrackCards().remove(goalTrackCard);
		goalTrackCard.setOrgGoalDefinition(null);

		return goalTrackCard;
	}


	//bi-directional many-to-one association to GoalValueType
	@OneToMany(mappedBy="orgGoalDefinition")
	public List<GoalValueType> getGoalValueTypes() {
		return this.goalValueTypes;
	}

	public void setGoalValueTypes(List<GoalValueType> goalValueTypes) {
		this.goalValueTypes = goalValueTypes;
	}

	public GoalValueType addGoalValueType(GoalValueType goalValueType) {
		getGoalValueTypes().add(goalValueType);
		goalValueType.setOrgGoalDefinition(this);

		return goalValueType;
	}

	public GoalValueType removeGoalValueType(GoalValueType goalValueType) {
		getGoalValueTypes().remove(goalValueType);
		goalValueType.setOrgGoalDefinition(null);

		return goalValueType;
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
	@JoinColumn(name="parent_id")
	public OrgGoalDefinition getOrgGoalDefinition() {
		return this.orgGoalDefinition;
	}

	public void setOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		this.orgGoalDefinition = orgGoalDefinition;
	}


	//bi-directional many-to-one association to OrgGoalDefinition
	@OneToMany(mappedBy="orgGoalDefinition")
	public List<OrgGoalDefinition> getOrgGoalDefinitions() {
		return this.orgGoalDefinitions;
	}

	public void setOrgGoalDefinitions(List<OrgGoalDefinition> orgGoalDefinitions) {
		this.orgGoalDefinitions = orgGoalDefinitions;
	}

	public OrgGoalDefinition addOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		getOrgGoalDefinitions().add(orgGoalDefinition);
		orgGoalDefinition.setOrgGoalDefinition(this);

		return orgGoalDefinition;
	}

	public OrgGoalDefinition removeOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		getOrgGoalDefinitions().remove(orgGoalDefinition);
		orgGoalDefinition.setOrgGoalDefinition(null);

		return orgGoalDefinition;
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


	//bi-directional many-to-one association to TrackedItemGoal
	@OneToMany(mappedBy="orgGoalDefinition")
	public List<TrackedItemGoal> getTrackedItemGoals() {
		return this.trackedItemGoals;
	}

	public void setTrackedItemGoals(List<TrackedItemGoal> trackedItemGoals) {
		this.trackedItemGoals = trackedItemGoals;
	}

	public TrackedItemGoal addTrackedItemGoal(TrackedItemGoal trackedItemGoal) {
		getTrackedItemGoals().add(trackedItemGoal);
		trackedItemGoal.setOrgGoalDefinition(this);

		return trackedItemGoal;
	}

	public TrackedItemGoal removeTrackedItemGoal(TrackedItemGoal trackedItemGoal) {
		getTrackedItemGoals().remove(trackedItemGoal);
		trackedItemGoal.setOrgGoalDefinition(null);

		return trackedItemGoal;
	}

}