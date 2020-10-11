package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the goal_value_type database table.
 * 
 */
@Entity
@Table(name="goal_value_type")
@NamedQuery(name="GoalValueType.findAll", query="SELECT g FROM GoalValueType g")
public class GoalValueType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String goalDefinitionState;
	private String title;
	private ClientAccount clientAccount;
	private OrgGoalDefinition orgGoalDefinition;
	private OrgGoalDomain orgGoalDomain;
	private List<GoalValueTypeOption> goalValueTypeOptions;

	public GoalValueType() {
	}


	@Id
	@SequenceGenerator(name="GOAL_VALUE_TYPE_ID_GENERATOR", sequenceName="SEQ_GOAL_VALUE_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOAL_VALUE_TYPE_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="goal_definition_state", nullable=false, length=50)
	public String getGoalDefinitionState() {
		return this.goalDefinitionState;
	}

	public void setGoalDefinitionState(String goalDefinitionState) {
		this.goalDefinitionState = goalDefinitionState;
	}


	@Column(nullable=false, length=100)
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
	@JoinColumn(name="org_goal_definition_id")
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


	//bi-directional many-to-one association to GoalValueTypeOption
	@OneToMany(mappedBy="goalValueType")
	public List<GoalValueTypeOption> getGoalValueTypeOptions() {
		return this.goalValueTypeOptions;
	}

	public void setGoalValueTypeOptions(List<GoalValueTypeOption> goalValueTypeOptions) {
		this.goalValueTypeOptions = goalValueTypeOptions;
	}

	public GoalValueTypeOption addGoalValueTypeOption(GoalValueTypeOption goalValueTypeOption) {
		getGoalValueTypeOptions().add(goalValueTypeOption);
		goalValueTypeOption.setGoalValueType(this);

		return goalValueTypeOption;
	}

	public GoalValueTypeOption removeGoalValueTypeOption(GoalValueTypeOption goalValueTypeOption) {
		getGoalValueTypeOptions().remove(goalValueTypeOption);
		goalValueTypeOption.setGoalValueType(null);

		return goalValueTypeOption;
	}

}