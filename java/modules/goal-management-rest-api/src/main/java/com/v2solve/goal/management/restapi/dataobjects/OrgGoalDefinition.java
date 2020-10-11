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
	List<GoalTrackCard> goalTrackCards;
	List<GoalValueType> goalValueTypes;
	ClientAccount clientAccount1;
	ClientAccount clientAccount2;
	OrgGoalDefinition orgGoalDefinition;
	List<OrgGoalDefinition> orgGoalDefinitions;
	OrgGoalDomain orgGoalDomain;
	List<TrackedItemGoal> trackedItemGoals;
}