package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tracked_item_goal database table.
 * 
 */
@Entity
@Table(name="tracked_item_goal")
@NamedQuery(name="TrackedItemGoal.findAll", query="SELECT t FROM TrackedItemGoal t")
public class TrackedItemGoal extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private double currentValue;
	private Timestamp lastUpdatedTime;
	private ClientAccount clientAccount;
	private OrgGoalDefinition orgGoalDefinition;
	private TrackedItemToCard trackedItemToCard;
	private List<TrackedItemGoalHistory> trackedItemGoalHistories;

	public TrackedItemGoal() {
	}


	@Id
	@SequenceGenerator(name="TRACKED_ITEM_GOAL_ID_GENERATOR", sequenceName="SEQ_TRACKED_ITEM_GOAL_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRACKED_ITEM_GOAL_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="current_value", nullable=false)
	public double getCurrentValue() {
		return this.currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}


	@Column(name="last_updated_time", nullable=false)
	public Timestamp getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}


	//bi-directional many-to-one association to ClientAccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="last_uptated_by_client_account_id", nullable=false)
	public ClientAccount getClientAccount() {
		return this.clientAccount;
	}

	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}


	//bi-directional many-to-one association to OrgGoalDefinition
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="org_goal_definition_id", nullable=false)
	public OrgGoalDefinition getOrgGoalDefinition() {
		return this.orgGoalDefinition;
	}

	public void setOrgGoalDefinition(OrgGoalDefinition orgGoalDefinition) {
		this.orgGoalDefinition = orgGoalDefinition;
	}


	//bi-directional many-to-one association to TrackedItemToCard
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tracked_item_to_card_id", nullable=false)
	public TrackedItemToCard getTrackedItemToCard() {
		return this.trackedItemToCard;
	}

	public void setTrackedItemToCard(TrackedItemToCard trackedItemToCard) {
		this.trackedItemToCard = trackedItemToCard;
	}


	//bi-directional many-to-one association to TrackedItemGoalHistory
	@OneToMany(mappedBy="trackedItemGoal")
	public List<TrackedItemGoalHistory> getTrackedItemGoalHistories() {
		return this.trackedItemGoalHistories;
	}

	public void setTrackedItemGoalHistories(List<TrackedItemGoalHistory> trackedItemGoalHistories) {
		this.trackedItemGoalHistories = trackedItemGoalHistories;
	}

	public TrackedItemGoalHistory addTrackedItemGoalHistory(TrackedItemGoalHistory trackedItemGoalHistory) {
		getTrackedItemGoalHistories().add(trackedItemGoalHistory);
		trackedItemGoalHistory.setTrackedItemGoal(this);

		return trackedItemGoalHistory;
	}

	public TrackedItemGoalHistory removeTrackedItemGoalHistory(TrackedItemGoalHistory trackedItemGoalHistory) {
		getTrackedItemGoalHistories().remove(trackedItemGoalHistory);
		trackedItemGoalHistory.setTrackedItemGoal(null);

		return trackedItemGoalHistory;
	}

}