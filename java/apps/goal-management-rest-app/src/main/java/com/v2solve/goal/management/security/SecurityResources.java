package com.v2solve.goal.management.security;


/**
 * This class contains definitions of items which are the security resources defined by this application.
 * They mostly represent rows of data in different tables of the security model. An application which deals
 * with a different model will probably have this different.
 * @author Saurin Magiawala
 *
 */
public class SecurityResources 
{
	public static final String APPLICATION             		= "APPLICATION";
	public static final String ACTION                  		= "ACTION";
	public static final String RESOURCE                		= "RESOURCE";
	public static final String CLIENT                  		= "CLIENT";
	public static final String PERMISSION              		= "PERMISSION";
	public static final String CLIENT_ROLE             		= "CLIENT_ROLE";
	public static final String CLIENT_GROUP            		= "CLIENT_GROUP";
	public static final String CLIENT_GROUP_MEMBERSHIP 		= "CLIENT_GROUP_MEMBERSHIP";
	public static final String RESOURCE_DOMAIN         		= "RESOURCE_DOMAIN";
	public static final String RESOURCE_DOMAIN_TYPE    		= "RESOURCE_DOMAIN_TYPE";
	public static final String SCOPE_TYPE              		= "SCOPE_TYPE";
	public static final String ROLE_SCOPE              		= "ROLE_SCOPE";
	public static final String GROUP_ROLE_MEMBERSHIP   		= "GROUP_ROLE_MEMBERSHIP";
	public static final String PERMISSION_ROLE_MEMBERSHIP 	= "PERMISSION_ROLE_MEMBERSHIP";
	public static final String CHANGE_LOG					= "CHANGE_LOG";
	public static final String BASIC_AUTH_CLIENT			= "BASIC_AUTH_CLIENT";
	public static final String SECURITY_DB                  = "SECURITY_DB";
	public static final String ONBOARD_TRUSTED_APP          = "ONBOARD_TRUSTED_APP";
}
