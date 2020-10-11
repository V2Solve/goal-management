package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tracked_item_goal_history database table.
 * 
 */
@Entity
@Table(name="tracked_item_goal_history")
@NamedQuery(name="TrackedItemGoalHistory.findAll", query="SELECT t FROM TrackedItemGoalHistory t")
public class TrackedItemGoalHistory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Timestamp atTime;
	private double historicalValue;
	private TrackedItemGoal trackedItemGoal;

	public TrackedItemGoalHistory() {
	}


	@Id
	@SequenceGenerator(name="TRACKED_ITEM_GOAL_HISTORY_ID_GENERATOR", sequenceName="SEQ_TRACKED_ITEM_GOAL_HISTORY_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRACKED_ITEM_GOAL_HISTORY_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="at_time", nullable=false)
	public Timestamp getAtTime() {
		return this.atTime;
	}

	public void setAtTime(Timestamp atTime) {
		this.atTime = atTime;
	}


	@Column(name="historical_value", nullable=false)
	public double getHistoricalValue() {
		return this.historicalValue;
	}

	public void setHistoricalValue(double historicalValue) {
		this.historicalValue = historicalValue;
	}


	//bi-directional many-to-one association to TrackedItemGoal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tracked_item_goal_id", nullable=false)
	public TrackedItemGoal getTrackedItemGoal() {
		return this.trackedItemGoal;
	}

	public void setTrackedItemGoal(TrackedItemGoal trackedItemGoal) {
		this.trackedItemGoal = trackedItemGoal;
	}

}