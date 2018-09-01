package com.planetpif.tekkenator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "MOVES")
public class Move {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	private String name;
	private String command;
	private String hitLevel;
	private String damage;
	private String startUpFrame;
	private String blockFrame;
	private String hitFrame;
	private String counterHitFrame;
	private String notes;
	private String alias;
	private Boolean safeOnBlock; // TODO boolean naming conventions?
	private String gifUrl;
	


	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fighter_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
	private Fighter fighter;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public String getMoveDetails() {
		String details = "";

		details += "Name: " + getName() + "\n";
		details += "Command: " + getCommand() + "\n";
		details += "Hit: " + getHitFrame() + "\n";
		details += "Safe on Block: " + getSafeOnBlock() + "\n";

		return details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getHitLevel() {
		return hitLevel;
	}

	public void setHitLevel(String hitLevel) {
		this.hitLevel = hitLevel;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public String getStartUpFrame() {
		return startUpFrame;
	}

	public void setStartUpFrame(String startUpFrame) {
		this.startUpFrame = startUpFrame;
	}

	public String getBlockFrame() {
		return blockFrame;
	}

	public void setBlockFrame(String blockFrame) {
		this.blockFrame = blockFrame;
	}

	public String getHitFrame() {
		return hitFrame;
	}

	public void setHitFrame(String hitFrame) {
		this.hitFrame = hitFrame;
	}

	public String getCounterHitFrame() {
		return counterHitFrame;
	}

	public void setCounterHitFrame(String counterHitFrame) {
		this.counterHitFrame = counterHitFrame;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Boolean getSafeOnBlock() {
		return safeOnBlock;
	}

	public void setSafeOnBlock(Boolean safeOnBlock) {
		this.safeOnBlock = safeOnBlock;
	}
	
	public String getGifUrl() {
		return gifUrl;
	}

	public void setGifUrl(String gifUrl) {
		this.gifUrl = gifUrl;
	}

}
