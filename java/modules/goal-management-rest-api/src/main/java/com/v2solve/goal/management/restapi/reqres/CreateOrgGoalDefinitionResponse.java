package com.v2solve.goal.management.restapi.reqres;

import com.v2solve.commons.base.restmodel.BaseResponse;
import com.v2solve.commons.base.restmodel.PagingInformation;
import com.v2solve.commons.base.restmodel.RequestStatusInformation;
import com.v2solve.goal.management.restapi.dataobjects.OrgGoalDefinition;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateOrgGoalDefinitionResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateOrgGoalDefinitionResponse(RequestStatusInformation rsi) {
		super(rsi);
	}

	public CreateOrgGoalDefinitionResponse(RequestStatusInformation rsi, PagingInformation pageInfo) {
		super(rsi, pageInfo);
	}

	OrgGoalDefinition goalInfo;	// Returns the object that was created if at all..
}
