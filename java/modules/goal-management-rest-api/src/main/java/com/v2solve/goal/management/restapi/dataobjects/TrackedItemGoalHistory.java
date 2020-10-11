package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tracked_item_goal_history database table.
 * 
 */
@Data
@NoArgsConstructor
public class TrackedItemGoalHistory implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	Timestamp atTime;
	double historicalValue;
	TrackedItemGoal trackedItemGoal;
}