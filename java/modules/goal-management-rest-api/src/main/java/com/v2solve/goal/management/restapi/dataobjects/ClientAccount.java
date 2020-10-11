package com.v2solve.goal.management.restapi.dataobjects;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


/**
 * The persistent class for the client_account database table.
 * 
 */
@Data
@NoArgsConstructor
public class ClientAccount implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	long id;
	Boolean emailVerified;
	String firstName;
	String lastName;
	String primaryEmail;
	String uniqueDisplayName;
	List<ClientIdentities> clientIdentities;
	List<GoalTrackCard> goalTrackCards;
	List<GoalValueType> goalValueTypes;
	List<GoalValueTypeOption> goalValueTypeOptions;
	OrgGoalDefinition orgGoalDefinition;
	List<OrgGoalDefinition> orgGoalDefinitions;
	List<OrgGoalDomain> orgGoalDomains;
	List<TrackedItem> trackedItems;
	List<TrackedItemGoal> trackedItemGoals;
}