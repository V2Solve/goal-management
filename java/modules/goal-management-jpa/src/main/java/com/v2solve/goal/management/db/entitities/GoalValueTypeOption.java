package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the goal_value_type_options database table.
 * 
 */
@Entity
@Table(name="goal_value_type_options")
@NamedQuery(name="GoalValueTypeOption.findAll", query="SELECT g FROM GoalValueTypeOption g")
public class GoalValueTypeOption extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private double optionValue;
	private String title;
	private ClientAccount clientAccount;
	private GoalValueType goalValueType;

	public GoalValueTypeOption() {
	}


	@Id
	@SequenceGenerator(name="GOAL_VALUE_TYPE_OPTIONS_ID_GENERATOR", sequenceName="SEQ_GOAL_VALUE_TYPE_OPTIONS_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOAL_VALUE_TYPE_OPTIONS_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="option_value", nullable=false)
	public double getOptionValue() {
		return this.optionValue;
	}

	public void setOptionValue(double optionValue) {
		this.optionValue = optionValue;
	}


	@Column(nullable=false, length=50)
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


	//bi-directional many-to-one association to GoalValueType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="goal_value_type_id", nullable=false)
	public GoalValueType getGoalValueType() {
		return this.goalValueType;
	}

	public void setGoalValueType(GoalValueType goalValueType) {
		this.goalValueType = goalValueType;
	}

}