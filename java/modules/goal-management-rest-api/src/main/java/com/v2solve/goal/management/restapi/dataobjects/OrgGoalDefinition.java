package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrgGoalDefinition implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	String description;
	String goalDefinitionState;
	double goalWeight;
	String title;

	OrgGoalDefinition parentGoal;
	List<OrgGoalDefinition> childGoals;
	
	List<GoalTrackCard> goalTrackCards;
	List<GoalValueType> goalValueTypes;
	ClientAccount clientAccount;
	OrgGoalDomain orgGoalDomain;
	List<TrackedItemGoal> trackedItemGoals;
}