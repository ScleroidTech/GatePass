package com.scleroidtech.gatepass.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

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
public class Visitor extends Person {
    @PrimaryKey(autoGenerate = true)
    private long visitId;
    private Date EntryTime;
    private Date ExitTime;
    private String modeOfTravel;
    @Nullable
    private String vehicleNo;
    private String purposeOfVisit;
    private String personToVisit;
    private long serialNoVisitor;

    public Visitor(String name, String address, String company, String mobileNo, String imageUrl, String proofUrl, Date entryTime, Date exitTime, String modeOfTravel, String vehicleNo, String purposeOfVisit, String personToVisit, long serialNoVisitor) {
        super(name, address, company, mobileNo, imageUrl, proofUrl);
        EntryTime = entryTime;
        ExitTime = exitTime;
        this.modeOfTravel = modeOfTravel;
        this.vehicleNo = vehicleNo;
        this.purposeOfVisit = purposeOfVisit;
        this.personToVisit = personToVisit;
        this.serialNoVisitor = serialNoVisitor;
    }

    public Visitor(String name, String address, String company, String mobileNo, String imageUrl, String proofUrl, String modeOfTravel, String vehicleNo, String purposeOfVisit, String personToVisit) {
        super(name, address, company, mobileNo, imageUrl, proofUrl);
        this.modeOfTravel = modeOfTravel;
        this.vehicleNo = vehicleNo;
        this.purposeOfVisit = purposeOfVisit;
        this.personToVisit = personToVisit;
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public Date getEntryTime() {
        return EntryTime;
    }

    public void setEntryTime(Date entryTime) {
        EntryTime = entryTime;
    }

    public Date getExitTime() {
        return ExitTime;
    }

    public void setExitTime(Date exitTime) {
        ExitTime = exitTime;
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
