package com.v2solve.goal.management.restapi.reqres;

import com.v2solve.commons.base.restmodel.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteOrgGoalDefinitionRequest extends BaseRequest 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id of the object to be deleted..
	 */
	String objectId;

}