package com.v2solve.goal.management.restapi.dataobjects;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the tracked_item database table.
 * 
 */
@Data
@NoArgsConstructor
public class TrackedItem implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	String description;
	String uniqueName;
	ClientAccount clientAccount;
	OrgGoalDomain orgGoalDomain;
	List<TrackedItemGoal> trackedItemGoals;
	List<TrackedItemToCard> trackedItemToCards;
}