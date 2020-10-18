package com.v2solve.goal.management.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.v2solve.commons.base.restmodel.ApiValidationException;
import com.v2solve.commons.base.restmodel.RequestStatusInformation;
import com.v2solve.commons.utils.ExceptionUtils;
import com.v2solve.commons.utils.GUIDUtils;
import com.v2solve.goal.management.businesslogic.OrgDomainBusinessLogic;
import com.v2solve.goal.management.restapi.contract.IGoalManagementApi;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;
import com.v2solve.goal.management.restapi.reqres.CreateOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.CreateOrgDomainResponse;
import com.v2solve.goal.management.restapi.reqres.DeleteOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.DeleteOrgDomainResponse;
import com.v2solve.goal.management.restapi.reqres.SearchOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.SearchOrgDomainResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(path = "/v1/goalmanagement", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
public class RestApiController implements IGoalManagementApi 
{
	static final String programLogicError = "Internal Logic Error: ";
	
	@Autowired OrgDomainBusinessLogic orgDomainService;

	@Override
	@RequestMapping(path = "/createOrgDomain")
	public CreateOrgDomainResponse createOrgDomain (@RequestBody CreateOrgDomainRequest request) 
	{
		try
		{
			OrgGoalDomain result = orgDomainService.createOrgGoalDomain(request.getDomainInfo());
			CreateOrgDomainResponse resp = new CreateOrgDomainResponse(RequestStatusInformation.SUCCESS);
			resp.setDomainInfo(result);
			return resp;
		}
		catch (ApiValidationException apie)
		{
			log.error(apie.getMessage());
			CreateOrgDomainResponse resp = new CreateOrgDomainResponse(RequestStatusInformation.failure(apie.getMessage()));
			return resp;
		}
		catch (Throwable e)
		{
			String failureMessage = programLogicError + GUIDUtils.getGUID();
			log.error(failureMessage);
			log.error(ExceptionUtils.getTrace(e));
			CreateOrgDomainResponse resp = new CreateOrgDomainResponse(RequestStatusInformation.failure(failureMessage));
			return resp;
		}
	}

	@Override
	@RequestMapping(path = "/searchOrgDomain")
	public SearchOrgDomainResponse searchOrgDomain(@RequestBody SearchOrgDomainRequest request) 
	{
		try
		{
			List<OrgGoalDomain> result = orgDomainService.searchOrgGoalDomain(request.getDomainInfo(),request.getPagingInfo());
			SearchOrgDomainResponse resp = new SearchOrgDomainResponse(RequestStatusInformation.SUCCESS);
			resp.setDomainInfo(result);
			return resp;
		}
		catch (ApiValidationException apie)
		{
			log.error(apie.getMessage());
			SearchOrgDomainResponse codr = new SearchOrgDomainResponse(RequestStatusInformation.failure(apie.getMessage()));
			return codr;
		}
		catch (Throwable e)
		{
			String failureMessage = programLogicError + GUIDUtils.getGUID();
			log.error(failureMessage);
			log.error(ExceptionUtils.getTrace(e));
			SearchOrgDomainResponse codr = new SearchOrgDomainResponse(RequestStatusInformation.failure(failureMessage));
			return codr;
		}
	}

	@Override
	@RequestMapping(path = "/deleteOrgDomain")
	public DeleteOrgDomainResponse deleteOrgDomain(@RequestBody DeleteOrgDomainRequest request) 
	{
		try
		{
			OrgGoalDomain result = orgDomainService.deleteOrgGoalDomain(request.getObjectId());
			DeleteOrgDomainResponse resp = new DeleteOrgDomainResponse(RequestStatusInformation.SUCCESS);
			resp.setDomainInfo(result);
			return resp;
		}
		catch (ApiValidationException apie)
		{
			log.error(apie.getMessage());
			DeleteOrgDomainResponse resp = new DeleteOrgDomainResponse(RequestStatusInformation.failure(apie.getMessage()));
			return resp;
		}
		catch (Throwable e)
		{
			String failureMessage = programLogicError + GUIDUtils.getGUID();
			log.error(failureMessage);
			log.error(ExceptionUtils.getTrace(e));
			DeleteOrgDomainResponse resp = new DeleteOrgDomainResponse(RequestStatusInformation.failure(failureMessage));
			return resp;
		}
	}
}
