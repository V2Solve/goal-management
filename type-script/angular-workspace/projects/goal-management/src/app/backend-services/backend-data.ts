import * as baserestapi from "../shared-services/rest-api-data";

/**
 * The persistent class for the client_account database table.
 * 
 */
export class ClientAccount
{
	id: number;
	emailVerified!: boolean;
	firstName!: string;
	lastName!: string;
	primaryEmail!: string;
	uniqueDisplayName!: string;
	clientIdentities!: Array<ClientIdentities>;
	goalTrackCards!: Array<GoalTrackCard>;
	goalValueTypes!: Array<GoalValueType>;
	goalValueTypeOptions!: Array<GoalValueTypeOption>;
	orgGoalDefinition!: OrgGoalDefinition;
	orgGoalDefinitions!: Array<OrgGoalDefinition>;
	orgGoalDomains!: Array<OrgGoalDomain>;
	trackedItems!: Array<TrackedItem>;
	trackedItemGoals!: Array<TrackedItemGoal>;
}

/**
 * The persistent class for the client_identities database table.
 * 
 */
export class ClientIdentities
{
	id!: number;
	clientAccount!: ClientAccount;
	clientIdentity!: ClientIdentity;
}

/**
 * The persistent class for the client_identity database table.
 * 
 */
export class ClientIdentity 
{
	id!: number;
	idpId!: string;
	lastLogin!: Date;
	uniqueIdentityId!: string;
	clientIdentities!: Array<ClientIdentities>;
}
/**
 * The persistent class for the goal_management_audit_log database table.
 * 
 */


export class GoalManagementAuditLog  
{
	
	id!: number;
	action!: string;
	atTime!: Date;
	byClientAccountId!: number;
	clientUniqueDisplayName!: string;
	finalRecord!: string;
	originalRecord!: string;
	resource!: string;
}


/**
 * The persistent class for the goal_track_card database table.
 * 
 */

export class GoalTrackCard  
{
	id!: number;
	description!: string;
	title!: string;
	clientAccount!: ClientAccount
	orgGoalDefinition!: OrgGoalDefinition
	orgGoalDomain!: OrgGoalDomain
	trackedItemToCards!:  Array<TrackedItemToCard>;
}



export class GoalValueType  
{
	id!: number;
	goalDefinitionState!: string;
	title!: string;
	clientAccount!: ClientAccount
	orgGoalDefinition!: OrgGoalDefinition
	orgGoalDomain!: OrgGoalDomain
	goalValueTypeOptions!: Array<GoalValueTypeOption>;
}



/**
 * The persistent class for the goal_value_type_options database table.
 * 
 */

export class GoalValueTypeOption  
{
	
	id!: number;
	optionValue!: number;
	title!: string;
	clientAccount!: ClientAccount
	goalValueType!: GoalValueType;
}


export class OrgGoalDefinition  
{
	id!: number;
	description!: string;
	goalDefinitionState!: string;
	goalWeight!: number;
	title!: string;
	goalTrackCards!: Array<GoalTrackCard>;
	goalValueTypes!: Array<GoalValueType>;
	clientAccount1!: ClientAccount;
	clientAccount2!: ClientAccount;
	orgGoalDefinition!: OrgGoalDefinition
	orgGoalDefinitions!: Array<OrgGoalDefinition>;
	orgGoalDomain!: OrgGoalDomain
	trackedItemGoals!: Array<TrackedItemGoal>;
}


/**
 * The persistent class for the org_goal_domain database table.
 * 
 */
export class OrgGoalDomain  
{
	id!: number;
	description!: string;
	title!: string;
	goalTrackCards!: Array<GoalTrackCard>;
	goalValueTypes!: Array<GoalValueType>;
	orgGoalDefinitions!: Array<OrgGoalDefinition>;
	clientAccount!: ClientAccount
	trackedItems!: Array<TrackedItem>;
}

/**
 * The persistent class for the tracked_item database table.
 * 
 */
export class TrackedItem  
{
	id!: number;
	description!: string;
	uniqueName!: string;
	clientAccount!: ClientAccount
	orgGoalDomain!: OrgGoalDomain
	trackedItemGoals!: Array<TrackedItemGoal>;
	trackedItemToCards!:  Array<TrackedItemToCard>;
}


/**
 * The persistent class for the tracked_item_goal database table.
 * 
 */

export class TrackedItemGoal  
{
	id!: number;
	currentValue!: number;
	lastUpdatedTime!: Date;
	clientAccount!: ClientAccount
	orgGoalDefinition!: OrgGoalDefinition
	trackedItem!: TrackedItem;
	trackedItemGoalHistories!: Array<TrackedItemGoalHistory>;
}



/**
 * The persistent class for the tracked_item_goal_history database table.
 * 
 */
export class TrackedItemGoalHistory  
{
	
	id!: number;
	atTime!: Date;
	historicalValue!: number;
	trackedItemGoal!: TrackedItemGoal;
}


/**
 * The persistent class for the tracked_item_to_card database table.
 * 
 */

export class TrackedItemToCard  
{
	id!: number;
	goalTrackCard!: GoalTrackCard;
	trackedItem!: TrackedItem;
}


/**
 * Request Response Messages for the Back End Services..
 */
export class CreateOrgDomainRequest extends baserestapi.BaseRequest
{

	/**
	 * 
	 */
	
	
	// The org domain to be created..
	domainInfo!: OrgGoalDomain;
}

export class CreateOrgDomainResponse extends baserestapi.BaseResponse {

	/**
	 * 
	 */
	domainInfo!: OrgGoalDomain;	// Returns the object that was created if at all..
}


export class DeleteOrgDomainRequest extends baserestapi.BaseRequest 
{

	/**
	 * 
	 */
	
	
	/**
	 * Id of the object to be deleted..
	 */
	objectId!: string;
}

export class DeleteOrgDomainResponse extends baserestapi.BaseResponse 
{
	/**
	 * 
	 */
	domainInfo!: OrgGoalDomain;	      // Returns the deleted object information..
}


export class SearchOrgDomainRequest extends CreateOrgDomainRequest {
    /**
	 * 
	 */
}

export class SearchOrgDomainResponse extends baserestapi.BaseResponse 
{
	/**
	 * 
	 */
	domainInfo!: Array<OrgGoalDomain>;	      // Returns the objects of the search..
}
