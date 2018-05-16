package com.scleroidtech.gatepass.data.local.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.scleroidtech.gatepass.utils.roomConverters.DateConverter;

import java.util.Date;

import javax.annotation.Nullable;

/**
 * Copyright (C)
 *
 * @author Ganesh Kaple
 * @since 3/13/18
 */


@Entity(foreignKeys = @ForeignKey(entity = Person.class,
        parentColumns = "serialNo",
		childColumns = "serialNoVisitor"))
public class Visit {
	@PrimaryKey(autoGenerate = true)
	private long visitId;
	@TypeConverters(DateConverter.class)
	private Date entryTime;
	@TypeConverters(DateConverter.class)
	private Date exitTime;
	private String modeOfTravel;
	@Nullable
	private String vehicleNo;
	private String purposeOfVisit;
	private String personToVisit;
	private long serialNoVisitor;

	public Visit(Date entryTime, Date exitTime, String modeOfTravel, String vehicleNo,
	             String purposeOfVisit, String personToVisit, long serialNoVisitor) {
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.modeOfTravel = modeOfTravel;
		this.vehicleNo = vehicleNo;
		this.purposeOfVisit = purposeOfVisit;
		this.personToVisit = personToVisit;
		this.serialNoVisitor = serialNoVisitor;
	}

	public long getVisitId() {
		return visitId;
	}

	public void setVisitId(long visitId) {
		this.visitId = visitId;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getExitTime() {
		return exitTime;
	}

	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}

	public long getSerialNoVisitor() {
		return serialNoVisitor;
	}

	public void setSerialNoVisitor(long serialNoVisitor) {
		this.serialNoVisitor = serialNoVisitor;
	}

	public String getModeOfTravel() {
		return modeOfTravel;
	}

	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}

	@Nullable
	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(@Nullable String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getPurposeOfVisit() {
		return purposeOfVisit;
	}

	public void setPurposeOfVisit(String purposeOfVisit) {
		this.purposeOfVisit = purposeOfVisit;
	}

	public String getPersonToVisit() {
		return personToVisit;
	}

	public void setPersonToVisit(String personToVisit) {
		this.personToVisit = personToVisit;
	}
}
