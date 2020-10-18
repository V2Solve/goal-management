package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tracked_item_to_card database table.
 * 
 */
@Entity
@Table(name="tracked_item_to_card")
@NamedQuery(name="TrackedItemToCard.findAll", query="SELECT t FROM TrackedItemToCard t")
public class TrackedItemToCard extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private List<TrackedItemGoal> trackedItemGoals;
	private GoalTrackCard goalTrackCard;
	private TrackedItem trackedItem;

	public TrackedItemToCard() {
	}


	@Id
	@SequenceGenerator(name="TRACKED_ITEM_TO_CARD_ID_GENERATOR", sequenceName="SEQ_TRACKED_ITEM_TO_CARD_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRACKED_ITEM_TO_CARD_ID_GENERATOR")
	@Column(unique=true, nullable=false, length=22)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	//bi-directional many-to-one association to TrackedItemGoal
	@OneToMany(mappedBy="trackedItemToCard")
	public List<TrackedItemGoal> getTrackedItemGoals() {
		return this.trackedItemGoals;
	}

	public void setTrackedItemGoals(List<TrackedItemGoal> trackedItemGoals) {
		this.trackedItemGoals = trackedItemGoals;
	}

	public TrackedItemGoal addTrackedItemGoal(TrackedItemGoal trackedItemGoal) {
		getTrackedItemGoals().add(trackedItemGoal);
		trackedItemGoal.setTrackedItemToCard(this);

		return trackedItemGoal;
	}

	public TrackedItemGoal removeTrackedItemGoal(TrackedItemGoal trackedItemGoal) {
		getTrackedItemGoals().remove(trackedItemGoal);
		trackedItemGoal.setTrackedItemToCard(null);

		return trackedItemGoal;
	}


	//bi-directional many-to-one association to GoalTrackCard
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="goal_track_card_id", nullable=false)
	public GoalTrackCard getGoalTrackCard() {
		return this.goalTrackCard;
	}

	public void setGoalTrackCard(GoalTrackCard goalTrackCard) {
		this.goalTrackCard = goalTrackCard;
	}


	//bi-directional many-to-one association to TrackedItem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tracked_item_id", nullable=false)
	public TrackedItem getTrackedItem() {
		return this.trackedItem;
	}

	public void setTrackedItem(TrackedItem trackedItem) {
		this.trackedItem = trackedItem;
	}

}