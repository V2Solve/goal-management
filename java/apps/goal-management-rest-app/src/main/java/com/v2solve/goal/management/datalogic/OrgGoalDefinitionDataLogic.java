package com.v2solve.goal.management.datalogic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.util.StringUtils;

import com.v2solve.commons.base.restmodel.PagingInformation;
import com.v2solve.commons.jpautils.DatalogicValidationException;
import com.v2solve.commons.jpautils.JPAUtils;
import com.v2solve.commons.jpautils.JPAUtils.COMPARATORS;
import com.v2solve.commons.jpautils.JPAUtils.LOGICAL;
import com.v2solve.goal.management.db.entitities.ClientAccount;
import com.v2solve.goal.management.db.entitities.OrgGoalDomain;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDefinition;

public class OrgGoalDefinitionDataLogic 
{
	/**
	 * Creates or Updates the OrgGoalDefinition in the system. If the title exists.. then it tries to update it.
	 * @param em
	 * @param recordToCreate
	 * @return
	 */
	public static com.v2solve.goal.management.db.entitities.OrgGoalDefinition createOrUpdateOrgGoalDefinition (EntityManager em,OrgGoalDefinition recordToCreate)
	{
		com.v2solve.goal.management.db.entitities.OrgGoalDefinition ogd = null;
		com.v2solve.goal.management.db.entitities.OrgGoalDefinition pd = null;
		
		// lets find the parent if any..
		OrgGoalDefinition parentRecord = recordToCreate.getParentGoal();
		
		if (parentRecord != null)
		{
			pd = JPAUtils.findObjectReturnNull(em, com.v2solve.goal.management.db.entitities.OrgGoalDefinition.class, "title", parentRecord.getTitle());
			if (pd == null)
				throw new DatalogicValidationException("Parent with title: " + parentRecord.getTitle() + " was not found.");
		}
		
		ClientAccount ca = null;
		
		if (recordToCreate.getClientAccount() != null)
		{
			ca = JPAUtils.findObjectReturnNull(em, ClientAccount.class, "uniqueDisplayName", recordToCreate.getClientAccount().getUniqueDisplayName());
			if (ca == null)
				throw new DatalogicValidationException("Client Account with : " + recordToCreate.getClientAccount().getUniqueDisplayName() + " was not found.");
		}
		
		
		OrgGoalDomain owningDomain = null;
		
		if (recordToCreate.getOwningGoalDomain() != null && !StringUtils.isEmpty(recordToCreate.getOwningGoalDomain().getTitle() != null))
		{
			owningDomain = JPAUtils.findObjectReturnNull(em, OrgGoalDomain.class, "title", recordToCreate.getOwningGoalDomain().getTitle());
			if (owningDomain == null)
				throw new DatalogicValidationException("Owning domain with title: " + recordToCreate.getOwningGoalDomain().getTitle() + " was not found.");
		}
		
		
		// Lets check if domain info already exists..
		ogd = JPAUtils.findObjectReturnNull(em, com.v2solve.goal.management.db.entitities.OrgGoalDefinition.class, "title", recordToCreate.getTitle());
		
		if (ogd == null)
		{
			ogd = new com.v2solve.goal.management.db.entitities.OrgGoalDefinition();
			ogd.setClientAccount(ca);
			ogd.setDescription(recordToCreate.getDescription());
			ogd.setTitle(recordToCreate.getTitle());
			ogd.setOrgGoalDefinition(pd);
			ogd.setOrgGoalDomain(owningDomain);
			ogd.setGoalWeight(recordToCreate.getGoalWeight());
			ogd.setGoalDefinitionState(recordToCreate.getGoalDefinitionState());
			JPAUtils.createObject(em, ogd);
		}
		else
		{
			ogd.setClientAccount(ca);
			ogd.setDescription(recordToCreate.getDescription());
			ogd.setTitle(recordToCreate.getTitle());
			
			if (pd != null && pd.getId() == ogd.getId())
				pd = null;	// It cannot be its Own PARENT !, this means that it will become root.
			
			ogd.setOrgGoalDefinition(pd);
			ogd.setOrgGoalDomain(owningDomain);
			ogd.setGoalWeight(recordToCreate.getGoalWeight());
			ogd.setGoalDefinitionState(recordToCreate.getGoalDefinitionState());
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
	public static com.v2solve.goal.management.db.entitities.OrgGoalDefinition deleteOrgGoalDefinition(EntityManager em,String title) 
	{
		// Lets find the Object First...
		com.v2solve.goal.management.db.entitities.OrgGoalDefinition ogd = JPAUtils.findObjectReturnNull(em, com.v2solve.goal.management.db.entitities.OrgGoalDefinition.class, "title", title);
		if (ogd == null)
			throw new DatalogicValidationException("Record with title: " + title + " was not found.");
		
		// Cool, so we found the object. Lets try to remove it..
		ogd = JPAUtils.deleteObject(em, ogd.getId(), com.v2solve.goal.management.db.entitities.OrgGoalDefinition.class);
		
		return ogd;
	}
	
	
	/**
	 * Searches the Org Goal Domains depending upon which fields have been provided in the recordToCreate Object.
	 * @param em
	 * @param recordToCreate
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static List<com.v2solve.goal.management.db.entitities.OrgGoalDefinition> searchOrgGoalDefinitions (EntityManager em,OrgGoalDefinition recordToSearch,PagingInformation pagingInfo) 
	throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
	
		Class<com.v2solve.goal.management.db.entitities.OrgGoalDefinition> clzz = com.v2solve.goal.management.db.entitities.OrgGoalDefinition.class;
		List<com.v2solve.goal.management.db.entitities.OrgGoalDefinition> listOfObjects = JPAUtils.searchEntities(em,clzz, recordToSearch, COMPARATORS.EQUALS, LOGICAL.OR, pagingInfo);
		return listOfObjects;
	}
}
