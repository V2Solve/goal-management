package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the client_identity database table.
 * 
 */
@Entity
@Table(name="client_identity")
@NamedQuery(name="ClientIdentity.findAll", query="SELECT c FROM ClientIdentity c")
public class ClientIdentity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String idpId;
	private Timestamp lastLogin;
	private String uniqueIdentityId;
	private List<ClientIdentities> clientIdentities;

	public ClientIdentity() {
	}


	@Id
	@SequenceGenerator(name="CLIENT_IDENTITY_ID_GENERATOR", sequenceName="SEQ_CLIENT_IDENTITY_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENT_IDENTITY_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="idp_id", nullable=false, length=100)
	public String getIdpId() {
		return this.idpId;
	}

	public void setIdpId(String idpId) {
		this.idpId = idpId;
	}


	@Column(name="last_login", nullable=false)
	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}


	@Column(name="unique_identity_id", nullable=false, length=100)
	public String getUniqueIdentityId() {
		return this.uniqueIdentityId;
	}

	public void setUniqueIdentityId(String uniqueIdentityId) {
		this.uniqueIdentityId = uniqueIdentityId;
	}


	//bi-directional many-to-one association to ClientIdentities
	@OneToMany(mappedBy="clientIdentity")
	public List<ClientIdentities> getClientIdentities() {
		return this.clientIdentities;
	}

	public void setClientIdentities(List<ClientIdentities> clientIdentities) {
		this.clientIdentities = clientIdentities;
	}

	public ClientIdentities addClientIdentity(ClientIdentities clientIdentity) {
		getClientIdentities().add(clientIdentity);
		clientIdentity.setClientIdentity(this);

		return clientIdentity;
	}

	public ClientIdentities removeClientIdentity(ClientIdentities clientIdentity) {
		getClientIdentities().remove(clientIdentity);
		clientIdentity.setClientIdentity(null);

		return clientIdentity;
	}

}