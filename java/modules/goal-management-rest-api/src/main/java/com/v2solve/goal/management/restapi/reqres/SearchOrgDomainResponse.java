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

	List<OrgGoalDomain> domainInfos;	      // Returns the objects of the search..
}
