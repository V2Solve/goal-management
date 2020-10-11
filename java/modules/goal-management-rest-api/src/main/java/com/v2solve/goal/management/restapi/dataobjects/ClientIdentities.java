package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * The persistent class for the client_identities database table.
 * 
 */
@Data
@NoArgsConstructor
public class ClientIdentities implements Serializable 
{
	static final long serialVersionUID = 1L;
	long id;
	ClientAccount clientAccount;
	ClientIdentity clientIdentity;
}