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
