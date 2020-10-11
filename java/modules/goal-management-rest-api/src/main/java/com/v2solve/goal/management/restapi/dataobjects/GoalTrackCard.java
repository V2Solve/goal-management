package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the goal_track_card database table.
 * 
 */
@Data
@NoArgsConstructor
public class GoalTrackCard implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	String description;
	String title;
	ClientAccount clientAccount;
	OrgGoalDefinition orgGoalDefinition;
	OrgGoalDomain orgGoalDomain;
	List<TrackedItemToCard> trackedItemToCards;

}