package com.v2solve.goal.management.restapi.dataobjects;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tracked_item_to_card database table.
 * 
 */
@Data
@NoArgsConstructor
public class TrackedItemToCard implements Serializable 
{
	static final long serialVersionUID = 1L;
	String id;
	GoalTrackCard goalTrackCard;
	TrackedItem trackedItem;
}