package com.v2solve.goal.management.restapi.dataobjects;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the goal_value_type_options database table.
 * 
 */
@Data
@NoArgsConstructor
public class GoalValueTypeOption implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	double optionValue;
	String title;
	ClientAccount clientAccount;
	GoalValueType goalValueType;
}