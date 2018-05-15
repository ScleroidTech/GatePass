package com.scleroidtech.gatepass.data.local.model;

/**
 * Copyright (C) 3/13/18
 * Author ganesh
 */

import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nullable;

/**
 * Model Class for an individual Person
 */
/*@Entity*/
public class Person {

	@PrimaryKey(autoGenerate = true)
	private long serialNo;
	private String name;
	private String address;
	@Nullable
	private String company;
	private String mobileNo;
	@Nullable
	private String imageUrl;
	@Nullable
	private String proofUrl;

	public Person(String name, String address, String company, String mobileNo, String imageUrl,
	              String proofUrl) {
		this.name = name;
		this.address = address;
		this.company = company;
		this.mobileNo = mobileNo;
		this.imageUrl = imageUrl;
		this.proofUrl = proofUrl;
	}

	public long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Nullable
	public String getCompany() {
		return company;
	}

	public void setCompany(@Nullable String company) {
		this.company = company;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Nullable
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(@Nullable String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Nullable
	public String getProofUrl() {
		return proofUrl;
	}

	public void setProofUrl(@Nullable String proofUrl) {
		this.proofUrl = proofUrl;
	}
}
