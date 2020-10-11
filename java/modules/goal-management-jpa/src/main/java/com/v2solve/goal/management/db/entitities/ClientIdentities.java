package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the client_identities database table.
 * 
 */
@Entity
@Table(name="client_identities")
@NamedQuery(name="ClientIdentities.findAll", query="SELECT c FROM ClientIdentities c")
public class ClientIdentities extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private ClientAccount clientAccount;
	private ClientIdentity clientIdentity;

	public ClientIdentities() {
	}


	@Id
	@SequenceGenerator(name="CLIENT_IDENTITIES_ID_GENERATOR", sequenceName="SEQ_CLIENT_IDENTITIES_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENT_IDENTITIES_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	//bi-directional many-to-one association to ClientAccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_account_id", nullable=false)
	public ClientAccount getClientAccount() {
		return this.clientAccount;
	}

	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}


	//bi-directional many-to-one association to ClientIdentity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_identity_id", nullable=false)
	public ClientIdentity getClientIdentity() {
		return this.clientIdentity;
	}

	public void setClientIdentity(ClientIdentity clientIdentity) {
		this.clientIdentity = clientIdentity;
	}

}