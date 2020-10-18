package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the org_goal_domain database table.
 * 
 */
@Entity
@Table(name="org_goal_domain")
@NamedQuery(name="OrgGoalDomain.findAll", query="SELECT o FROM OrgGoalDomain o")
public class OrgGoalDomain extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String description;
	private String title;
	private List<GoalTrackCard> goalTrackCards;
	private List<GoalValueType> goalValueTypes;
	private List<OrgGoalDefinition> orgGoalDefinitions;
	private ClientAccount clientAccount;
	private OrgGoalDomain orgGoalDomain;
	private List<OrgGoalDomain> orgGoalDomains;
	private List<TrackedItem> trackedItems;

	public OrgGoalDomain() {
	}


	@Id
	@SequenceGenerator(name="ORG_GOAL_DOMAIN_ID_GENERATOR", sequenceName="SEQ_ORG_GOAL_DOMAIN_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORG_GOAL_DOMAIN_ID_GENERATOR")
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


	//bi-directional many-to-one association to GoalTrackCard
	@OneToMany(mappedBy="orgGoalDomain")
	public List<GoalTrackCard> getGoalTrackCards() {
		return this.goalTrackCards;
	}

	public void setGoalTrackCards(List<GoalTrackCard> goalTrackCards) {
		this.goalTrackCards = goalTrackCards;
	}

	public GoalTrackCard addGoalTrackCard(GoalTrackCard goalTrackCard) {
		getGoalTrackCards().add(goalTrackCard);
		goalTrackCard.setOrgGoalDomain(this);

		return goalTrackCard;
	}

	public GoalTrackCard removeGoalTrackCard(GoalTrackCard goalTrackCard) {
		getGoalTrackCards().remove(goalTrackCard);
		goalTrackCard.setOrgGoalDomain(null);

		return goalTrackCard;
	}


	//bi-directional many-to-one association to GoalValueType
	@OneToMany(mappedBy="orgGoalDomain")
	public List<GoalValueType> getGoalValueTypes() {
		return this.goalValueTypes;
	}

	public void setGoalValueTypes(List<GoalValueType> goalValueTypes) {
		this.goalValueTypes = goalValueTypes;
	}

	public GoalValueType addGoalValueType(GoalValueType goalValueType) {
		getGoalValueTypes().add(goalValueType);
		goalValueType.setOrgGoalDomain(this);

		return goalValueType;
	}

	public GoalValueType removeGoalValueType(GoalValueType goalValueType) {
		getGoalValueTypes().remove(goalValueType);
		goalValueType.setOrgGoalDomain(null);

		return goalValueType;
	}


	//bi-directional many-to-one association to OrgGoalDefinition
	@OneToMany(mappedBy="orgGoalDomain")
	public List<OrgGoalDefinition> getOrgGoalDefinitions() {
		return this.orgGoalDefinitions;
	}

	public void setOrgGoalDefinitions(List<OrgGoalDefinition> orgGoalDefinitions) {
		this.orgGoalDefinitions = orgGoalDefinitions;
	}

	public OrgGoalDefinition addOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		getOrgGoalDefinitions().add(orgGoalDefinition);
		orgGoalDefinition.setOrgGoalDomain(this);

		return orgGoalDefinition;
	}

	public OrgGoalDefinition removeOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		getOrgGoalDefinitions().remove(orgGoalDefinition);
		orgGoalDefinition.setOrgGoalDomain(null);

		return orgGoalDefinition;
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


	//bi-directional many-to-one association to OrgGoalDomain
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public OrgGoalDomain getOrgGoalDomain() {
		return this.orgGoalDomain;
	}

	public void setOrgGoalDomain(OrgGoalDomain orgGoalDomain) {
		this.orgGoalDomain = orgGoalDomain;
	}


	//bi-directional many-to-one association to OrgGoalDomain
	@OneToMany(mappedBy="orgGoalDomain")
	public List<OrgGoalDomain> getOrgGoalDomains() {
		return this.orgGoalDomains;
	}

	public void setOrgGoalDomains(List<OrgGoalDomain> orgGoalDomains) {
		this.orgGoalDomains = orgGoalDomains;
	}

	public OrgGoalDomain addOrgGoalDomain(OrgGoalDomain orgGoalDomain) {
		getOrgGoalDomains().add(orgGoalDomain);
		orgGoalDomain.setOrgGoalDomain(this);

		return orgGoalDomain;
	}

	public OrgGoalDomain removeOrgGoalDomain(OrgGoalDomain orgGoalDomain) {
		getOrgGoalDomains().remove(orgGoalDomain);
		orgGoalDomain.setOrgGoalDomain(null);

		return orgGoalDomain;
	}


	//bi-directional many-to-one association to TrackedItem
	@OneToMany(mappedBy="orgGoalDomain")
	public List<TrackedItem> getTrackedItems() {
		return this.trackedItems;
	}

	public void setTrackedItems(List<TrackedItem> trackedItems) {
		this.trackedItems = trackedItems;
	}

	public TrackedItem addTrackedItem(TrackedItem trackedItem) {
		getTrackedItems().add(trackedItem);
		trackedItem.setOrgGoalDomain(this);

		return trackedItem;
	}

	public TrackedItem removeTrackedItem(TrackedItem trackedItem) {
		getTrackedItems().remove(trackedItem);
		trackedItem.setOrgGoalDomain(null);

		return trackedItem;
	}

}