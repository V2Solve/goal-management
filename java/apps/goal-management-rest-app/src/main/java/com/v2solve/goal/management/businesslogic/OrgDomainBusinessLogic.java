package com.v2solve.goal.management.businesslogic;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;

@Service
public class OrgDomainBusinessLogic 
{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	/**
	 * Creates a new OrgGoalDomain Object
	 * @param domainInfo
	 * @return
	 */
	public OrgGoalDomain createOrgGoalDomain (OrgGoalDomain domainInfo)
	{
		return null;
	}
	
	
	/**
	 * Delete a OrgGoalDomain identified by the ID.
	 * @param domainInfo
	 * @return
	 */
	public OrgGoalDomain deleteOrgGoalDomain (String objectId)
	{
		return null;
	}
	
	/**
	 * Searches the OrgGoalDomain based on the what is filled in the domainInfo..
	 * @param domainInfo
	 * @return
	 */
	public List<OrgGoalDomain> searchOrgGoalDomain (OrgGoalDomain domainInfo)
	{
		return null;
	}
	
}