package com.v2solve.goal.management.businesslogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.v2solve.commons.base.restmodel.BusinesslogicValidationException;
import com.v2solve.commons.base.restmodel.PagingInformation;
import com.v2solve.commons.jpautils.TransactionWrapper;
import com.v2solve.commons.utils.Errors;
import com.v2solve.goal.management.datalogic.OrgDomainDataLogic;
import com.v2solve.goal.management.restapi.dataobjects.ClientAccount;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;

@Service
public class OrgDomainBusinessLogic 
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
	
	void validateForCreateAndOrUpdate (EntityManager em,OrgGoalDomain domainInfo)
	throws BusinesslogicValidationException
	{
		Errors errs = new Errors();
		// Lets check that client account is present..
		ClientAccount ca = domainInfo.getClientAccount();
		
		if (ca == null)
			throw new BusinesslogicValidationException("Client Account was not set on OrgGoalDomain object.");
		
		String uniqueName = ca.getUniqueDisplayName();
		if (StringUtils.isEmpty(uniqueName))
			errs.add("Client Account unique name was not provided.");

		String pEmail = ca.getPrimaryEmail();
		if (StringUtils.isEmpty(pEmail))
			errs.add("Primary email of the client account not provided.");
		
		if (StringUtils.isEmpty(domainInfo.getDescription()))
			errs.add("Domain Description is required.");
				
		if (StringUtils.isEmpty(domainInfo.getTitle()))
			errs.add("Domain Title is required.");
		
		if (errs.hasErrors())
			throw new BusinesslogicValidationException(errs.getConsolidatedErrors("Errors encountered while validating Domain Information."));
	}
	
	
	public void copyObject (com.v2solve.goal.management.db.entitities.OrgGoalDomain src,OrgGoalDomain dest)
	{
		dest.setTitle(src.getTitle());
		dest.setDescription(src.getDescription());
		dest.setId(src.getId());
		
		com.v2solve.goal.management.db.entitities.OrgGoalDomain parentDomain = src.getOrgGoalDomain();
		
		// Lets give the parent.
		if (parentDomain != null)
		{
			OrgGoalDomain pdd = new OrgGoalDomain();
			pdd.setTitle(parentDomain.getTitle());
			pdd.setDescription(parentDomain.getDescription());
			pdd.setId(parentDomain.getId());
			dest.setParentDomain(pdd);
		}
		
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
	 * Creates a new OrgGoalDomain Object
	 * @param domainInfo
	 * @return
	 */
	public OrgGoalDomain createOrgGoalDomain (OrgGoalDomain domainInfo)
	{
		EntityManager em = null;
		TransactionWrapper tw = null;
		
		try
		{
			em = getEm();
			tw = TransactionWrapper.getTransactionWrapper(em);
			validateForCreateAndOrUpdate(em, domainInfo);
			com.v2solve.goal.management.db.entitities.OrgGoalDomain ogd = OrgDomainDataLogic.createOrUpdateOrgGoalDomain(em, domainInfo);
			tw.success();
			domainInfo = new OrgGoalDomain();
			copyObject(ogd, domainInfo);
			return domainInfo;
		}
		finally
		{
			TransactionWrapper.commitAndClose(tw, em);
		}
	}
	
	
	/**
	 * Delete a OrgGoalDomain identified by the ID.
	 * @param domainInfo
	 * @return
	 */
	public OrgGoalDomain deleteOrgGoalDomain (String objectId)
	{
		EntityManager em = null;
		TransactionWrapper tw = null;
		try
		{
			em = getEm();
			tw = TransactionWrapper.getTransactionWrapper(em);
			validateForDelete(em, objectId);
			com.v2solve.goal.management.db.entitities.OrgGoalDomain ogd = OrgDomainDataLogic.deleteOrgDomain(em, objectId);
			tw.success();
			OrgGoalDomain domainInfo = new OrgGoalDomain();
			copyObject(ogd, domainInfo);
			return domainInfo;
		}
		finally
		{
			TransactionWrapper.commitAndClose(tw, em);
		}
	}
	
	/**
	 * Searches the OrgGoalDomain based on the what is filled in the domainInfo..
	 * @param domainInfo
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public List<OrgGoalDomain> searchOrgGoalDomain (OrgGoalDomain domainInfo,PagingInformation pagingInfo) 
	throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{

		EntityManager em = null;
		TransactionWrapper tw = null;
		List<OrgGoalDomain> results = new ArrayList<>();
		
		try
		{
			em = getEm();
			tw = TransactionWrapper.getTransactionWrapper(em);
			
			List<com.v2solve.goal.management.db.entitities.OrgGoalDomain> listOfObjects = OrgDomainDataLogic.searchOrgGoalDomains(em, domainInfo, pagingInfo);
			
			if (listOfObjects != null)
			{
				for (com.v2solve.goal.management.db.entitities.OrgGoalDomain ogd: listOfObjects)
				{
					OrgGoalDomain ogdd = new OrgGoalDomain();
					copyObject(ogd, ogdd);
					results.add(ogdd);
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