package com.v2solve.goal.management.datalogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;

import com.v2solve.commons.base.restmodel.PagingInformation;
import com.v2solve.commons.jpautils.DatalogicValidationException;
import com.v2solve.commons.jpautils.JPAUtils;
import com.v2solve.commons.jpautils.JPAUtils.COMPARATORS;
import com.v2solve.commons.jpautils.JPAUtils.LOGICAL;
import com.v2solve.goal.management.db.entitities.ClientAccount;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;

public class OrgDomainDataLogic 
{
	
	
	/**
	 * Creates or Updates the OrgGoalDomain in the system. If the title exists.. then it tries to update it.
	 * @param em
	 * @param domainInfo
	 * @return
	 */
	public static com.v2solve.goal.management.db.entitities.OrgGoalDomain createOrUpdateOrgGoalDomain (EntityManager em,OrgGoalDomain domainInfo)
	{
		com.v2solve.goal.management.db.entitities.OrgGoalDomain ogd = null;
		com.v2solve.goal.management.db.entitities.OrgGoalDomain pd = null;
		
		// lets find the parent if any..
		OrgGoalDomain parentDomain = domainInfo.getParentDomain();
		
		if (parentDomain != null)
		{
			pd = JPAUtils.findObjectReturnNull(em, com.v2solve.goal.management.db.entitities.OrgGoalDomain.class, "title", parentDomain.getTitle());
			if (pd == null)
				throw new DatalogicValidationException("Parent Domain with title: " + parentDomain.getTitle() + " was not found.");
		}
		
		ClientAccount ca = null;
		
		if (domainInfo.getClientAccount() != null)
		{
			ca = JPAUtils.findObjectReturnNull(em, ClientAccount.class, "uniqueDisplayName", domainInfo.getClientAccount().getUniqueDisplayName());
			if (ca == null)
				throw new DatalogicValidationException("Client Account with : " + domainInfo.getClientAccount().getUniqueDisplayName() + " was not found.");
		}
		
		// Lets check if domain info already exists..
		ogd = JPAUtils.findObjectReturnNull(em, com.v2solve.goal.management.db.entitities.OrgGoalDomain.class, "title", domainInfo.getTitle());
		
		if (ogd == null)
		{
			ogd = new com.v2solve.goal.management.db.entitities.OrgGoalDomain();
			ogd.setClientAccount(ca);
			ogd.setDescription(domainInfo.getDescription());
			ogd.setTitle(domainInfo.getTitle());
			ogd.setOrgGoalDomain(pd);
			JPAUtils.createObject(em, ogd);
		}
		else
		{
			ogd.setClientAccount(ca);
			ogd.setDescription(domainInfo.getDescription());
			ogd.setTitle(domainInfo.getTitle());
			ogd.setOrgGoalDomain(pd);
			JPAUtils.updateObject(em, ogd);
		}
		
		return ogd;
	}

	
	/**
	 * Removes the Object from database having this title..
	 * @param em
	 * @param title
	 * @return
	 */
	public static com.v2solve.goal.management.db.entitities.OrgGoalDomain deleteOrgDomain(EntityManager em,String title) 
	{
		// Lets find the Object First...
		com.v2solve.goal.management.db.entitities.OrgGoalDomain ogd = JPAUtils.findObjectReturnNull(em, com.v2solve.goal.management.db.entitities.OrgGoalDomain.class, "title", title);
		if (ogd == null)
			throw new DatalogicValidationException("Domain with title: " + title + " was not found.");
		
		// Cool, so we found the object. Lets try to remove it..
		ogd = JPAUtils.deleteObject(em, ogd.getId(), com.v2solve.goal.management.db.entitities.OrgGoalDomain.class);
		
		return ogd;
	}
	
	
	/**
	 * Searches the Org Goal Domains depending upon which fields have been provided in the domainInfo Object.
	 * @param em
	 * @param domainInfo
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static List<com.v2solve.goal.management.db.entitities.OrgGoalDomain> searchOrgGoalDomains (EntityManager em,OrgGoalDomain domainInfo,PagingInformation pagingInfo) 
	throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
	
		List<OrgGoalDomain> results = new ArrayList<>();
		Class<com.v2solve.goal.management.db.entitities.OrgGoalDomain> clzz = com.v2solve.goal.management.db.entitities.OrgGoalDomain.class;
		List<com.v2solve.goal.management.db.entitities.OrgGoalDomain> listOfObjects = JPAUtils.searchEntities(em,clzz, domainInfo, COMPARATORS.EQUALS, LOGICAL.OR, pagingInfo);
		return listOfObjects;
	}
}
