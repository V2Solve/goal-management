package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the client_account database table.
 * 
 */
@Entity
@Table(name="client_account")
@NamedQuery(name="ClientAccount.findAll", query="SELECT c FROM ClientAccount c")
public class ClientAccount extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Boolean emailVerified;
	private String firstName;
	private String lastName;
	private String primaryEmail;
	private String uniqueDisplayName;
	private List<ClientIdentities> clientIdentities;
	private List<GoalTrackCard> goalTrackCards;
	private List<GoalValueType> goalValueTypes;
	private List<GoalValueTypeOption> goalValueTypeOptions;
	private OrgGoalDefinition orgGoalDefinition;
	private List<OrgGoalDefinition> orgGoalDefinitions;
	private List<OrgGoalDomain> orgGoalDomains;
	private List<TrackedItem> trackedItems;
	private List<TrackedItemGoal> trackedItemGoals;

	public ClientAccount() {
	}


	@Id
	@SequenceGenerator(name="CLIENT_ACCOUNT_ID_GENERATOR", sequenceName="SEQ_CLIENT_ACCOUNT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENT_ACCOUNT_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="email_verified", nullable=false)
	public Boolean getEmailVerified() {
		return this.emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}


	@Column(name="first_name", nullable=false, length=50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="last_name", nullable=false, length=50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(name="primary_email", nullable=false, length=255)
	public String getPrimaryEmail() {
		return this.primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}


	@Column(name="unique_display_name", nullable=false, length=50)
	public String getUniqueDisplayName() {
		return this.uniqueDisplayName;
	}

	public void setUniqueDisplayName(String uniqueDisplayName) {
		this.uniqueDisplayName = uniqueDisplayName;
	}


	//bi-directional many-to-one association to ClientIdentities
	@OneToMany(mappedBy="clientAccount")
	public List<ClientIdentities> getClientIdentities() {
		return this.clientIdentities;
	}

	public void setClientIdentities(List<ClientIdentities> clientIdentities) {
		this.clientIdentities = clientIdentities;
	}

	public ClientIdentities addClientIdentity(ClientIdentities clientIdentity) {
		getClientIdentities().add(clientIdentity);
		clientIdentity.setClientAccount(this);

		return clientIdentity;
	}

	public ClientIdentities removeClientIdentity(ClientIdentities clientIdentity) {
		getClientIdentities().remove(clientIdentity);
		clientIdentity.setClientAccount(null);

		return clientIdentity;
	}


	//bi-directional many-to-one association to GoalTrackCard
	@OneToMany(mappedBy="clientAccount")
	public List<GoalTrackCard> getGoalTrackCards() {
		return this.goalTrackCards;
	}

	public void setGoalTrackCards(List<GoalTrackCard> goalTrackCards) {
		this.goalTrackCards = goalTrackCards;
	}

	public GoalTrackCard addGoalTrackCard(GoalTrackCard goalTrackCard) {
		getGoalTrackCards().add(goalTrackCard);
		goalTrackCard.setClientAccount(this);

		return goalTrackCard;
	}

	public GoalTrackCard removeGoalTrackCard(GoalTrackCard goalTrackCard) {
		getGoalTrackCards().remove(goalTrackCard);
		goalTrackCard.setClientAccount(null);

		return goalTrackCard;
	}


	//bi-directional many-to-one association to GoalValueType
	@OneToMany(mappedBy="clientAccount")
	public List<GoalValueType> getGoalValueTypes() {
		return this.goalValueTypes;
	}

	public void setGoalValueTypes(List<GoalValueType> goalValueTypes) {
		this.goalValueTypes = goalValueTypes;
	}

	public GoalValueType addGoalValueType(GoalValueType goalValueType) {
		getGoalValueTypes().add(goalValueType);
		goalValueType.setClientAccount(this);

		return goalValueType;
	}

	public GoalValueType removeGoalValueType(GoalValueType goalValueType) {
		getGoalValueTypes().remove(goalValueType);
		goalValueType.setClientAccount(null);

		return goalValueType;
	}


	//bi-directional many-to-one association to GoalValueTypeOption
	@OneToMany(mappedBy="clientAccount")
	public List<GoalValueTypeOption> getGoalValueTypeOptions() {
		return this.goalValueTypeOptions;
	}

	public void setGoalValueTypeOptions(List<GoalValueTypeOption> goalValueTypeOptions) {
		this.goalValueTypeOptions = goalValueTypeOptions;
	}

	public GoalValueTypeOption addGoalValueTypeOption(GoalValueTypeOption goalValueTypeOption) {
		getGoalValueTypeOptions().add(goalValueTypeOption);
		goalValueTypeOption.setClientAccount(this);

		return goalValueTypeOption;
	}

	public GoalValueTypeOption removeGoalValueTypeOption(GoalValueTypeOption goalValueTypeOption) {
		getGoalValueTypeOptions().remove(goalValueTypeOption);
		goalValueTypeOption.setClientAccount(null);

		return goalValueTypeOption;
	}


	//bi-directional one-to-one association to OrgGoalDefinition
	@OneToOne(mappedBy="clientAccount1", fetch=FetchType.LAZY)
	public OrgGoalDefinition getOrgGoalDefinition() {
		return this.orgGoalDefinition;
	}

	public void setOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		this.orgGoalDefinition = orgGoalDefinition;
	}


	//bi-directional many-to-one association to OrgGoalDefinition
	@OneToMany(mappedBy="clientAccount2")
	public List<OrgGoalDefinition> getOrgGoalDefinitions() {
		return this.orgGoalDefinitions;
	}

	public void setOrgGoalDefinitions(List<OrgGoalDefinition> orgGoalDefinitions) {
		this.orgGoalDefinitions = orgGoalDefinitions;
	}

	public OrgGoalDefinition addOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		getOrgGoalDefinitions().add(orgGoalDefinition);
		orgGoalDefinition.setClientAccount2(this);

		return orgGoalDefinition;
	}

	public OrgGoalDefinition removeOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		getOrgGoalDefinitions().remove(orgGoalDefinition);
		orgGoalDefinition.setClientAccount2(null);

		return orgGoalDefinition;
	}


	//bi-directional many-to-one association to OrgGoalDomain
	@OneToMany(mappedBy="clientAccount")
	public List<OrgGoalDomain> getOrgGoalDomains() {
		return this.orgGoalDomains;
	}

	public void setOrgGoalDomains(List<OrgGoalDomain> orgGoalDomains) {
		this.orgGoalDomains = orgGoalDomains;
	}

	public OrgGoalDomain addOrgGoalDomain(OrgGoalDomain orgGoalDomain) {
		getOrgGoalDomains().add(orgGoalDomain);
		orgGoalDomain.setClientAccount(this);

		return orgGoalDomain;
	}

	public OrgGoalDomain removeOrgGoalDomain(OrgGoalDomain orgGoalDomain) {
		getOrgGoalDomains().remove(orgGoalDomain);
		orgGoalDomain.setClientAccount(null);

		return orgGoalDomain;
	}


	//bi-directional many-to-one association to TrackedItem
	@OneToMany(mappedBy="clientAccount")
	public List<TrackedItem> getTrackedItems() {
		return this.trackedItems;
	}

	public void setTrackedItems(List<TrackedItem> trackedItems) {
		this.trackedItems = trackedItems;
	}

	public TrackedItem addTrackedItem(TrackedItem trackedItem) {
		getTrackedItems().add(trackedItem);
		trackedItem.setClientAccount(this);

		return trackedItem;
	}

	public TrackedItem removeTrackedItem(TrackedItem trackedItem) {
		getTrackedItems().remove(trackedItem);
		trackedItem.setClientAccount(null);

		return trackedItem;
	}


	//bi-directional many-to-one association to TrackedItemGoal
	@OneToMany(mappedBy="clientAccount")
	public List<TrackedItemGoal> getTrackedItemGoals() {
		return this.trackedItemGoals;
	}

	public void setTrackedItemGoals(List<TrackedItemGoal> trackedItemGoals) {
		this.trackedItemGoals = trackedItemGoals;
	}

	public TrackedItemGoal addTrackedItemGoal(TrackedItemGoal trackedItemGoal) {
		getTrackedItemGoals().add(trackedItemGoal);
		trackedItemGoal.setClientAccount(this);

		return trackedItemGoal;
	}

	public TrackedItemGoal removeTrackedItemGoal(TrackedItemGoal trackedItemGoal) {
		getTrackedItemGoals().remove(trackedItemGoal);
		trackedItemGoal.setClientAccount(null);

		return trackedItemGoal;
	}

}