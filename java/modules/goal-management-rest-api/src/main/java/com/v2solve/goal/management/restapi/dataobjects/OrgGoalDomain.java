package com.v2solve.goal.management.restapi.dataobjects;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the org_goal_domain database table.
 * 
 */
@Data
@NoArgsConstructor
public class OrgGoalDomain implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	String description;
	String title;
	List<GoalTrackCard> goalTrackCards;
	List<GoalValueType> goalValueTypes;
	List<OrgGoalDefinition> orgGoalDefinitions;
	ClientAccount clientAccount;
	List<TrackedItem> trackedItems;
}