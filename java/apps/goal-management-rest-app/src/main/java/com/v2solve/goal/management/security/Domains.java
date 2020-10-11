package com.v2solve.goal.management.security;

import org.springframework.util.StringUtils;

import com.v2solve.app.security.securitymodel.Domain;

/**
 * Contains domain types and constants used for this application
 * @author Saurin Magiawala
 *
 */
public class Domains 
{
	public static String APP_DOMAIN_TYPE = "AppDomain";
	
	/**
	 * Constructs a domain with the domain name.
	 * BUT will return a null if the domainName is empty..
	 * @param domainName
	 * @return
	 */
	public static Domain appDomain(String domainName)
	{
		if (StringUtils.isEmpty(domainName))
			return null;
		
		return new Domain (domainName,APP_DOMAIN_TYPE);
	}
	
}
