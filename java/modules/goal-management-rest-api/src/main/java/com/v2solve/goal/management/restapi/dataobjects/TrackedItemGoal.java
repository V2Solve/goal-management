package com.v2solve.goal.management.restapi.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the tracked_item_goal database table.
 * 
 */
@Data
@NoArgsConstructor
public class TrackedItemGoal implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	double currentValue;
	Timestamp lastUpdatedTime;
	ClientAccount clientAccount;
	OrgGoalDefinition orgGoalDefinition;
	TrackedItem trackedItem;
	List<TrackedItemGoalHistory> trackedItemGoalHistories;
}