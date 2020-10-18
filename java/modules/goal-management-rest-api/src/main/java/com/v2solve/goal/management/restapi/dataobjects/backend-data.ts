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
}package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * The persistent class for the client_identities database table.
 * 
 */
@Data
@NoArgsConstructor
public class ClientIdentities implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	ClientAccount clientAccount;
	ClientIdentity clientIdentity;
}package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the client_identity database table.
 * 
 */
@Data
@NoArgsConstructor
public class ClientIdentity implements Serializable {
	
	static final long serialVersionUID = 1L;
	long id;
	String idpId;
	Timestamp lastLogin;
	String uniqueIdentityId;
	List<ClientIdentities> clientIdentities;
}package com.v2solve.goal.management.restapi.dataobjects;


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


}package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the goal_track_card database table.
 * 
 */
@Data
@NoArgsConstructor
public class GoalTrackCard implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	String description;
	String title;
	ClientAccount clientAccount;
	OrgGoalDefinition orgGoalDefinition;
	OrgGoalDomain orgGoalDomain;
	List<TrackedItemToCard> trackedItemToCards;

}package com.v2solve.goal.management.restapi.dataobjects;


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

}package com.v2solve.goal.management.restapi.dataobjects;

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
}package com.v2solve.goal.management.restapi.dataobjects;


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
}package com.v2solve.goal.management.restapi.dataobjects;

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
}package com.v2solve.goal.management.restapi.dataobjects;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the tracked_item database table.
 * 
 */
@Data
@NoArgsConstructor
public class TrackedItem implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	String description;
	String uniqueName;
	ClientAccount clientAccount;
	OrgGoalDomain orgGoalDomain;
	List<TrackedItemGoal> trackedItemGoals;
	List<TrackedItemToCard> trackedItemToCards;
}package com.v2solve.goal.management.restapi.dataobjects;

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
}package com.v2solve.goal.management.restapi.dataobjects;


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
}package com.v2solve.goal.management.restapi.dataobjects;

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