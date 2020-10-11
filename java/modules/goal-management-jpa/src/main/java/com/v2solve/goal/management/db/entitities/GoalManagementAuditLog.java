package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the goal_management_audit_log database table.
 * 
 */
@Entity
@Table(name="goal_management_audit_log")
@NamedQuery(name="GoalManagementAuditLog.findAll", query="SELECT g FROM GoalManagementAuditLog g")
public class GoalManagementAuditLog extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String action;
	private Timestamp atTime;
	private BigDecimal byClientAccountId;
	private String clientUniqueDisplayName;
	private String finalRecord;
	private String originalRecord;
	private String resource;

	public GoalManagementAuditLog() {
	}


	@Id
	@SequenceGenerator(name="GOAL_MANAGEMENT_AUDIT_LOG_ID_GENERATOR", sequenceName="SEQ_GOAL_MANAGEMENT_AUDIT_LOG_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOAL_MANAGEMENT_AUDIT_LOG_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(nullable=false, length=50)
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	@Column(name="at_time", nullable=false)
	public Timestamp getAtTime() {
		return this.atTime;
	}

	public void setAtTime(Timestamp atTime) {
		this.atTime = atTime;
	}


	@Column(name="by_client_account_id", nullable=false, precision=22)
	public BigDecimal getByClientAccountId() {
		return this.byClientAccountId;
	}

	public void setByClientAccountId(BigDecimal byClientAccountId) {
		this.byClientAccountId = byClientAccountId;
	}


	@Column(name="client_unique_display_name", nullable=false, length=100)
	public String getClientUniqueDisplayName() {
		return this.clientUniqueDisplayName;
	}

	public void setClientUniqueDisplayName(String clientUniqueDisplayName) {
		this.clientUniqueDisplayName = clientUniqueDisplayName;
	}


	@Column(name="final_record", nullable=false, length=1024)
	public String getFinalRecord() {
		return this.finalRecord;
	}

	public void setFinalRecord(String finalRecord) {
		this.finalRecord = finalRecord;
	}


	@Column(name="original_record", length=1024)
	public String getOriginalRecord() {
		return this.originalRecord;
	}

	public void setOriginalRecord(String originalRecord) {
		this.originalRecord = originalRecord;
	}


	@Column(nullable=false, length=50)
	public String getResource() {
		return this.resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}