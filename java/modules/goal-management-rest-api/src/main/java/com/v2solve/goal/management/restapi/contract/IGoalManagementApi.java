package com.v2solve.goal.management.restapi.contract;

import com.v2solve.goal.management.restapi.reqres.CreateOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.CreateOrgDomainResponse;
import com.v2solve.goal.management.restapi.reqres.DeleteOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.DeleteOrgDomainResponse;
import com.v2solve.goal.management.restapi.reqres.SearchOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.SearchOrgDomainResponse;

/**
 * Interface representing the Goal Management API..
 * @author Saurinya
 *
 */
public interface IGoalManagementApi 
{
	/**
	 * Should create a Org Domain as indicated in the OrgDomainRequest, and return the appropriate response.
	 * @param request
	 * @return
	 */
	CreateOrgDomainResponse createOrgDomain  (CreateOrgDomainRequest request);
	SearchOrgDomainResponse searchOrgDomain  (SearchOrgDomainRequest request);
	DeleteOrgDomainResponse deleteOrgDomain  (DeleteOrgDomainRequest request);
}
