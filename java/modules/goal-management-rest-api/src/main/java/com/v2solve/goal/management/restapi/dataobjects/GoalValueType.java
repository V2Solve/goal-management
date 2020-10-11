package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoalValueType implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	String goalDefinitionState;
	String title;
	ClientAccount clientAccount;
	OrgGoalDefinition orgGoalDefinition;
	OrgGoalDomain orgGoalDomain;
	List<GoalValueTypeOption> goalValueTypeOptions;

}