package com.v2solve.goal.management.restapi.dataobjects;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the client_identity database table.
 * 
 */
@Data
@NoArgsConstructor
public class ClientIdentity implements Serializable {
	
	static final long serialVersionUID = 1L;
	long id;
	String idpId;
	Timestamp lastLogin;
	String uniqueIdentityId;
	List<ClientIdentities> clientIdentities;
}