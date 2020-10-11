package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the goal_management_audit_log database table.
 * 
 */
@Data
@NoArgsConstructor
public class GoalManagementAuditLog implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	String action;
	Timestamp atTime;
	BigDecimal byClientAccountId;
	String clientUniqueDisplayName;
	String finalRecord;
	String originalRecord;
	String resource;


}