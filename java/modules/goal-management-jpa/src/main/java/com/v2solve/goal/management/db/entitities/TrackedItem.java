package com.v2solve.goal.management.db.entitities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tracked_item database table.
 * 
 */
@Entity
@Table(name="tracked_item")
@NamedQuery(name="TrackedItem.findAll", query="SELECT t FROM TrackedItem t")
public class TrackedItem extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String description;
	private String uniqueName;
	private ClientAccount clientAccount;
	private OrgGoalDomain orgGoalDomain;
	private List<TrackedItemToCard> trackedItemToCards;

	public TrackedItem() {
	}


	@Id
	@SequenceGenerator(name="TRACKED_ITEM_ID_GENERATOR", sequenceName="SEQ_TRACKED_ITEM_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRACKED_ITEM_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(nullable=false, length=1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="unique_name", nullable=false, length=255)
	public String getUniqueName() {
		return this.uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}


	//bi-directional many-to-one association to ClientAccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="owner_client_account_id", nullable=false)
	public ClientAccount getClientAccount() {
		return this.clientAccount;
	}

	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}


	//bi-directional many-to-one association to OrgGoalDomain
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="owning_org_goal_domain_id")
	public OrgGoalDomain getOrgGoalDomain() {
		return this.orgGoalDomain;
	}

	public void setOrgGoalDomain(OrgGoalDomain orgGoalDomain) {
		this.orgGoalDomain = orgGoalDomain;
	}


	//bi-directional many-to-one association to TrackedItemToCard
	@OneToMany(mappedBy="trackedItem")
	public List<TrackedItemToCard> getTrackedItemToCards() {
		return this.trackedItemToCards;
	}

	public void setTrackedItemToCards(List<TrackedItemToCard> trackedItemToCards) {
		this.trackedItemToCards = trackedItemToCards;
	}

	public TrackedItemToCard addTrackedItemToCard(TrackedItemToCard trackedItemToCard) {
		getTrackedItemToCards().add(trackedItemToCard);
		trackedItemToCard.setTrackedItem(this);

		return trackedItemToCard;
	}

	public TrackedItemToCard removeTrackedItemToCard(TrackedItemToCard trackedItemToCard) {
		getTrackedItemToCards().remove(trackedItemToCard);
		trackedItemToCard.setTrackedItem(null);

		return trackedItemToCard;
	}

}