package com.v2solve.goal.management.businesslogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.v2solve.commons.base.restmodel.BusinesslogicValidationException;
import com.v2solve.commons.base.restmodel.PagingInformation;
import com.v2solve.commons.jpautils.TransactionWrapper;
import com.v2solve.commons.utils.Errors;
import com.v2solve.goal.management.datalogic.OrgGoalDefinitionDataLogic;
import com.v2solve.goal.management.restapi.dataobjects.ClientAccount;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDefinition;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;


@Service
public class OrgGoalDefinitionBusinessLogic 
{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	
	EntityManager getEm ()
	{
		return entityManagerFactory.createEntityManager();
	}
	

	void validateForDelete (EntityManager em,String objectId)
	throws BusinesslogicValidationException
	{
		
	}
	
	void validateForCreateAndOrUpdate (EntityManager em,OrgGoalDefinition recordToCreate)
	throws BusinesslogicValidationException
	{
		Errors errs = new Errors();
		// Lets check that client account is present..
		ClientAccount ca = recordToCreate.getClientAccount();
		
		if (ca == null)
			throw new BusinesslogicValidationException("Client Account was not set on OrgGoalDefinition object.");
		
		String uniqueName = ca.getUniqueDisplayName();
		if (StringUtils.isEmpty(uniqueName))
			errs.add("Client Account unique name was not provided.");

		String pEmail = ca.getPrimaryEmail();
		if (StringUtils.isEmpty(pEmail))
			errs.add("Primary email of the client account not provided.");
		
		if (StringUtils.isEmpty(recordToCreate.getDescription()))
			errs.add("Domain Description is required.");
				
		if (StringUtils.isEmpty(recordToCreate.getTitle()))
			errs.add("Definition Title is required.");
		
		if (StringUtils.isEmpty(recordToCreate.getGoalDefinitionState()))
				errs.add("OrgGoalDefinition State property is required.");
		
		OrgGoalDomain ogd = recordToCreate.getOwningGoalDomain();
		if (ogd == null || StringUtils.isEmpty(ogd.getTitle()))
			errs.add("Owning Goal Domain must be specified.");
		
		
		
		if (errs.hasErrors())
			throw new BusinesslogicValidationException(errs.getConsolidatedErrors("Errors encountered while validating OrgGoalDefinition Information."));
	}
	
	
	void copyParentRecursive (com.v2solve.goal.management.db.entitities.OrgGoalDefinition src, OrgGoalDefinition dest)
	{
			if (src != null)
			{
				OrgGoalDefinition parent = new OrgGoalDefinition();
				parent.setTitle(src.getTitle());
				parent.setDescription(src.getDescription());
				parent.setId(src.getId());
				parent.setGoalWeight(src.getGoalWeight());
				
				OrgGoalDomain orgGoalDomain = new OrgGoalDomain();
				orgGoalDomain.setTitle(src.getOrgGoalDomain().getTitle());
				orgGoalDomain.setDescription(src.getOrgGoalDomain().getDescription());
				orgGoalDomain.setId(src.getOrgGoalDomain().getId());
				parent.setOwningGoalDomain(orgGoalDomain);
				
				ClientAccount ca = new ClientAccount();
				com.v2solve.goal.management.db.entitities.ClientAccount cae = src.getClientAccount();
				ca.setFirstName(cae.getFirstName());
				ca.setLastName(cae.getLastName());
				ca.setId(cae.getId());
				ca.setPrimaryEmail(cae.getPrimaryEmail());
				ca.setUniqueDisplayName(cae.getUniqueDisplayName());
				parent.setClientAccount(ca);
				dest.setParentGoal(parent);
				if (src.getOrgGoalDefinition() != null)
					copyParentRecursive(src.getOrgGoalDefinition(), parent);
			}
	}
	

	void copyChildrenRecursive (com.v2solve.goal.management.db.entitities.OrgGoalDefinition src, OrgGoalDefinition dest)
	{
		if (src != null)
		{
			List<com.v2solve.goal.management.db.entitities.OrgGoalDefinition> childrenDb = src.getOrgGoalDefinitions();
			
			if (childrenDb != null && childrenDb.size() > 0)
			{
				List<OrgGoalDefinition> children = new ArrayList<>();
				
				for (com.v2solve.goal.management.db.entitities.OrgGoalDefinition ogd: childrenDb)
				{
					OrgGoalDefinition child = new OrgGoalDefinition();
					child.setTitle(ogd.getTitle());
					child.setDescription(ogd.getDescription());
					child.setId(ogd.getId());
					child.setGoalWeight(ogd.getGoalWeight());
					
					OrgGoalDomain orgGoalDomain = new OrgGoalDomain();
					orgGoalDomain.setTitle(ogd.getOrgGoalDomain().getTitle());
					orgGoalDomain.setDescription(ogd.getOrgGoalDomain().getDescription());
					orgGoalDomain.setId(ogd.getOrgGoalDomain().getId());
					child.setOwningGoalDomain(orgGoalDomain);
					
					
					ClientAccount ca = new ClientAccount();
					com.v2solve.goal.management.db.entitities.ClientAccount cae = ogd.getClientAccount();
					ca.setFirstName(cae.getFirstName());
					ca.setLastName(cae.getLastName());
					ca.setId(cae.getId());
					ca.setPrimaryEmail(cae.getPrimaryEmail());
					ca.setUniqueDisplayName(cae.getUniqueDisplayName());
					child.setClientAccount(ca);
					children.add(child);
					copyChildrenRecursive(ogd, child);
				}
				
				dest.setChildGoals(children);
			}
		}
	}
	
	void copyObject (com.v2solve.goal.management.db.entitities.OrgGoalDefinition src,OrgGoalDefinition dest)
	{
		dest.setTitle(src.getTitle());
		dest.setDescription(src.getDescription());
		dest.setId(src.getId());
		dest.setGoalWeight(src.getGoalWeight());
		
		OrgGoalDomain orgGoalDomain = new OrgGoalDomain();
		orgGoalDomain.setTitle(src.getOrgGoalDomain().getTitle());
		orgGoalDomain.setDescription(src.getOrgGoalDomain().getDescription());
		orgGoalDomain.setId(src.getOrgGoalDomain().getId());
		dest.setOwningGoalDomain(orgGoalDomain);
		
		com.v2solve.goal.management.db.entitities.OrgGoalDefinition parentGoal = src.getOrgGoalDefinition();
		
		// Lets give the parent recursive.
		copyParentRecursive(parentGoal, dest);
		copyChildrenRecursive(src,dest);
		
		com.v2solve.goal.management.restapi.dataobjects.ClientAccount ca = new com.v2solve.goal.management.restapi.dataobjects.ClientAccount();
		com.v2solve.goal.management.db.entitities.ClientAccount caE = src.getClientAccount();
		
		ca.setFirstName(caE.getFirstName());
		ca.setLastName(caE.getLastName());
		ca.setPrimaryEmail(caE.getPrimaryEmail());
		ca.setUniqueDisplayName(caE.getUniqueDisplayName());
		ca.setId(caE.getId());
		dest.setClientAccount(ca);
	}
	
	
	/**
	 * Creates a new OrgGoalDefinition Object
	 * @param recordInfo
	 * @return
	 */
	public OrgGoalDefinition createOrgGoalDefinition (OrgGoalDefinition recordToCreate)
	{
		EntityManager em = null;
		TransactionWrapper tw = null;
		
		try
		{
			em = getEm();
			tw = TransactionWrapper.getTransactionWrapper(em);
			validateForCreateAndOrUpdate(em, recordToCreate);
			com.v2solve.goal.management.db.entitities.OrgGoalDefinition ogd = OrgGoalDefinitionDataLogic.createOrUpdateOrgGoalDefinition(em, recordToCreate);
			tw.success();
			recordToCreate = new OrgGoalDefinition();
			copyObject(ogd, recordToCreate);
			return recordToCreate;
		}
		finally
		{
			TransactionWrapper.commitAndClose(tw, em);
		}
	}
	
	
	/**
	 * Delete a OrgGoalDefinition identified by the ID.
	 * @param recordInfo
	 * @return
	 */
	public OrgGoalDefinition deleteOrgGoalDefinition (String objectId)
	{
		EntityManager em = null;
		TransactionWrapper tw = null;
		try
		{
			em = getEm();
			tw = TransactionWrapper.getTransactionWrapper(em);
			validateForDelete(em, objectId);
			com.v2solve.goal.management.db.entitities.OrgGoalDefinition ogd = OrgGoalDefinitionDataLogic.deleteOrgGoalDefinition(em, objectId);
			tw.success();
			OrgGoalDefinition record = new OrgGoalDefinition();
			copyObject(ogd, record);
			return record;
		}
		finally
		{
			TransactionWrapper.commitAndClose(tw, em);
		}
	}
	
	/**
	 * Searches the OrgGoalDefinition based on the what is filled in the recordInfo..
	 * @param recordInfo
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public List<OrgGoalDefinition> searchOrgGoalDefinition (OrgGoalDefinition recordInfo,PagingInformation pagingInfo) 
	throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{

		EntityManager em = null;
		TransactionWrapper tw = null;
		List<OrgGoalDefinition> results = new ArrayList<>();
		
		try
		{
			em = getEm();
			tw = TransactionWrapper.getTransactionWrapper(em);
			
			List<com.v2solve.goal.management.db.entitities.OrgGoalDefinition> listOfObjects = OrgGoalDefinitionDataLogic.searchOrgGoalDefinitions(em, recordInfo, pagingInfo);
			
			if (listOfObjects != null)
			{
				for (com.v2solve.goal.management.db.entitities.OrgGoalDefinition ogd: listOfObjects)
				{
					// if (ogd.getOrgGoalDefinition() == null)	// Lets only return top level..
					{
						OrgGoalDefinition ogdd = new OrgGoalDefinition();
						copyObject(ogd, ogdd);
						results.add(ogdd);
					}
				}
			}
		}
		finally
		{
			TransactionWrapper.commitAndClose(tw, em);
		}
		
		return results;
	}
	
}