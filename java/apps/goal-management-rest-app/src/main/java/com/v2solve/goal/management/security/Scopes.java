package com.v2solve.goal.management.security;

import com.v2solve.app.security.securitymodel.Scope;

/**
 * This class contains definitions of the scopes that are used for this application.
 * 
 *
 */
public class Scopes 
{
	// Values of the Scope..
	public static final String CLIENT_SCOPE_ALL    = "ALL";		// Indicates all records  
	public static final String CLIENT_SCOPE_OWN    = "OWN";	    // Own Records
	public static final String CLIENT_SCOPE_DOMAIN = "DOMAIN";	// Indicates domain level records.
	
	// Different Scope Types 
	public static final String SCOPE_ASSIGN_GROUP_TO_CLIENT               = "ASSIGN_GROUP_TO_CLIENT";
	public static final String SCOPE_ASSIGN_ROLE_TO_GROUP                 = "ASSIGN_ROLE_TO_GROUP";
	public static final String SCOPE_ASSIGN_PERMISSION_TO_ROLE            = "ASSIGN_PERMISSION_TO_ROLE";
	public static final String SCOPE_CLIENT_ROLE        				  = "CLIENT_ROLE";
	public static final String SCOPE_CLIENT_GROUP        			      = "CLIENT_GROUP";
	public static final String CHANGE_LOG_SCOPE_TYPE					  = "CHANGE_LOG";
	
	public static final String ASSIGNMENT_TYPE_ALLOW = "allow";
	public static final String ASSIGNMENT_TYPE_DENY  = "deny";
	
	
	public static Scope assignGroupToClientScope (String value,String assignmentType)
	{
		return new Scope ("NONAME",SCOPE_ASSIGN_GROUP_TO_CLIENT,value,null,assignmentType,null);
	}

	public static Scope assignRoleToGroupScope (String value,String assignmentType)
	{
		return new Scope ("NONAME",SCOPE_ASSIGN_ROLE_TO_GROUP,value,null,assignmentType,null);
	}

	public static Scope clientRoleScope (String value,String assignmentType)
	{
		return new Scope ("NONAME",SCOPE_CLIENT_ROLE,value,null,assignmentType,null);
	}
	
	public static Scope assignPermissionToRoleScope (String value,String assignmentType)
	{
		return new Scope ("NONAME",SCOPE_ASSIGN_PERMISSION_TO_ROLE,value,null,assignmentType,null);
	}
	

	public static Scope clientGroupScope (String value,String assignmentType)
	{
		return new Scope ("NONAME",SCOPE_CLIENT_GROUP,value,null,assignmentType,null);
	}
	
}
