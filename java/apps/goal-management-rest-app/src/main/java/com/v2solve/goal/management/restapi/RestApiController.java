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
import com.v2solve.goal.management.businesslogic.OrgGoalDefinitionBusinessLogic;
import com.v2solve.goal.management.restapi.contract.IGoalManagementApi;
import com.v2solve.goal.management.restapi.dataobjects.ClientAccount;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDefinition;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;
import com.v2solve.goal.management.restapi.reqres.CreateOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.CreateOrgDomainResponse;
import com.v2solve.goal.management.restapi.reqres.CreateOrgGoalDefinitionRequest;
import com.v2solve.goal.management.restapi.reqres.CreateOrgGoalDefinitionResponse;
import com.v2solve.goal.management.restapi.reqres.DeleteOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.DeleteOrgDomainResponse;
import com.v2solve.goal.management.restapi.reqres.DeleteOrgGoalDefinitionRequest;
import com.v2solve.goal.management.restapi.reqres.DeleteOrgGoalDefinitionResponse;
import com.v2solve.goal.management.restapi.reqres.SearchOrgDomainRequest;
import com.v2solve.goal.management.restapi.reqres.SearchOrgDomainResponse;
import com.v2solve.goal.management.restapi.reqres.SearchOrgGoalDefinitionRequest;
import com.v2solve.goal.management.restapi.reqres.SearchOrgGoalDefinitionResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(path = "/v1/goalmanagement", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
public class RestApiController implements IGoalManagementApi 
{
	static final String programLogicError = "Internal Logic Error: ";
	
	@Autowired OrgDomainBusinessLogic orgDomainService;
	@Autowired OrgGoalDefinitionBusinessLogic orgGoalService;
	
	ClientAccount getClientAccount ()
	{
		ClientAccount ca = new ClientAccount();
		ca.setFirstName("Saurin");
		ca.setLastName("Magiawala");
		ca.setPrimaryEmail("saurinya@gmail.com");
		ca.setUniqueDisplayName("saurinya");
		return ca;
	}

	@Override
	@RequestMapping(path = "/createOrgDomain")
	public CreateOrgDomainResponse createOrgDomain (@RequestBody CreateOrgDomainRequest request) 
	{
		try
		{
			OrgGoalDomain ogd = request.getDomainInfo();
			ogd.setClientAccount(getClientAccount());
			
			OrgGoalDomain result = orgDomainService.createOrgGoalDomain(ogd);
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
			resp.setDomainInfos(result);
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

	@Override
	@RequestMapping(path = "/createOrgGoalDefinition")
	public CreateOrgGoalDefinitionResponse createOrgGoalDefinition(@RequestBody CreateOrgGoalDefinitionRequest request) 
	{
		try
		{
			OrgGoalDefinition ogd = request.getGoalInfo();
			ogd.setClientAccount(getClientAccount());
			
			OrgGoalDefinition result = orgGoalService.createOrgGoalDefinition(ogd);
			CreateOrgGoalDefinitionResponse resp = new CreateOrgGoalDefinitionResponse(RequestStatusInformation.SUCCESS);
			resp.setGoalInfo(result);
			return resp;
		}
		catch (ApiValidationException apie)
		{
			log.error(apie.getMessage());
			CreateOrgGoalDefinitionResponse resp = new CreateOrgGoalDefinitionResponse(RequestStatusInformation.failure(apie.getMessage()));
			return resp;
		}
		catch (Throwable e)
		{
			String failureMessage = programLogicError + GUIDUtils.getGUID();
			log.error(failureMessage);
			log.error(ExceptionUtils.getTrace(e));
			CreateOrgGoalDefinitionResponse resp = new CreateOrgGoalDefinitionResponse(RequestStatusInformation.failure(failureMessage));
			return resp;
		}
	}

	@Override
	@RequestMapping(path = "/searchOrgGoalDefinition")
	public SearchOrgGoalDefinitionResponse searchOrgGoalDefinition(@RequestBody SearchOrgGoalDefinitionRequest request) 
	{
		try
		{
			List<OrgGoalDefinition> result = orgGoalService.searchOrgGoalDefinition(request.getGoalInfo(),request.getPagingInfo());
			SearchOrgGoalDefinitionResponse resp = new SearchOrgGoalDefinitionResponse(RequestStatusInformation.SUCCESS);
			resp.setGoalInfos(result);
			return resp;
		}
		catch (ApiValidationException apie)
		{
			log.error(apie.getMessage());
			SearchOrgGoalDefinitionResponse codr = new SearchOrgGoalDefinitionResponse(RequestStatusInformation.failure(apie.getMessage()));
			return codr;
		}
		catch (Throwable e)
		{
			String failureMessage = programLogicError + GUIDUtils.getGUID();
			log.error(failureMessage);
			log.error(ExceptionUtils.getTrace(e));
			SearchOrgGoalDefinitionResponse codr = new SearchOrgGoalDefinitionResponse(RequestStatusInformation.failure(failureMessage));
			return codr;
		}
	}

	@Override
	@RequestMapping(path = "/deleteOrgGoalDefinition")
	public DeleteOrgGoalDefinitionResponse deleteOrgGoalDefinition(@RequestBody DeleteOrgGoalDefinitionRequest request) 
	{
		try
		{
			OrgGoalDefinition result = orgGoalService.deleteOrgGoalDefinition(request.getObjectId());
			DeleteOrgGoalDefinitionResponse resp = new DeleteOrgGoalDefinitionResponse(RequestStatusInformation.SUCCESS);
			resp.setGoalInfo(result);
			return resp;
		}
		catch (ApiValidationException apie)
		{
			log.error(apie.getMessage());
			DeleteOrgGoalDefinitionResponse resp = new DeleteOrgGoalDefinitionResponse(RequestStatusInformation.failure(apie.getMessage()));
			return resp;
		}
		catch (Throwable e)
		{
			String failureMessage = programLogicError + GUIDUtils.getGUID();
			log.error(failureMessage);
			log.error(ExceptionUtils.getTrace(e));
			DeleteOrgGoalDefinitionResponse resp = new DeleteOrgGoalDefinitionResponse(RequestStatusInformation.failure(failureMessage));
			return resp;
		}
	}
}