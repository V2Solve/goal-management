package com.v2solve.goal.management.restapi.reqres;

import com.v2solve.commons.base.restmodel.BaseRequest;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateOrgDomainRequest extends BaseRequest 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// The org domain to be created..
	OrgGoalDomain domainInfo;
}
package com.v2solve.goal.management.restapi.reqres;

import com.v2solve.commons.base.restmodel.BaseResponse;
import com.v2solve.commons.base.restmodel.PagingInformation;
import com.v2solve.commons.base.restmodel.RequestStatusInformation;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateOrgDomainResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateOrgDomainResponse(RequestStatusInformation rsi) {
		super(rsi);
	}

	public CreateOrgDomainResponse(RequestStatusInformation rsi, PagingInformation pageInfo) {
		super(rsi, pageInfo);
	}

	OrgGoalDomain domainInfo;	// Returns the object that was created if at all..
}
package com.v2solve.goal.management.restapi.reqres;

import com.v2solve.commons.base.restmodel.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteOrgDomainRequest extends BaseRequest 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id of the object to be deleted..
	 */
	String objectId;

}package com.v2solve.goal.management.restapi.reqres;

import com.v2solve.commons.base.restmodel.BaseResponse;
import com.v2solve.commons.base.restmodel.PagingInformation;
import com.v2solve.commons.base.restmodel.RequestStatusInformation;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeleteOrgDomainResponse extends BaseResponse 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteOrgDomainResponse(RequestStatusInformation rsi) {
		super(rsi);
	}

	public DeleteOrgDomainResponse(RequestStatusInformation rsi, PagingInformation pageInfo) {
		super(rsi, pageInfo);
	}

	OrgGoalDomain domainInfo;	      // Returns the deleted object information..
}
package com.v2solve.goal.management.restapi.reqres;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchOrgDomainRequest extends CreateOrgDomainRequest {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
package com.v2solve.goal.management.restapi.reqres;

import java.util.List;

import com.v2solve.commons.base.restmodel.BaseResponse;
import com.v2solve.commons.base.restmodel.PagingInformation;
import com.v2solve.commons.base.restmodel.RequestStatusInformation;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SearchOrgDomainResponse extends BaseResponse 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchOrgDomainResponse(RequestStatusInformation rsi) {
		super(rsi);
	}

	public SearchOrgDomainResponse(RequestStatusInformation rsi, PagingInformation pageInfo) {
		super(rsi, pageInfo);
	}

	List<OrgGoalDomain> domainInfo;	      // Returns the objects of the search..
}
