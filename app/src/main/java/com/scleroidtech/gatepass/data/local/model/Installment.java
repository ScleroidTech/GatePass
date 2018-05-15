package com.scleroidtech.gatepass.data.local.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.scleroid.financematic.utils.roomConverters.DateConverter;
import com.scleroid.financematic.utils.roomConverters.MoneyConverter;

import java.math.BigDecimal;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/9/18
 */
@Entity(foreignKeys = @ForeignKey(entity = Loan.class,
		parentColumns = "accountNo",
		childColumns = "loanAcNo",
		onDelete = CASCADE),
		indices = {@Index(value = "installmentId", unique = true)})
public class Installment {

	@Ignore
	Loan loan;
	@PrimaryKey(autoGenerate = false)
	private int installmentId;

	@TypeConverters(DateConverter.class)
	private Date installmentDate;
	@TypeConverters(MoneyConverter.class)
	private BigDecimal expectedAmt;
	private int loanAcNo;
	private String delayReason;

	public Installment(final int installmentId, final Date installmentDate,
	                   final BigDecimal expectedAmt, final int loanAcNo,
	                   final String delayReason) {
		this.installmentId = installmentId;
		this.installmentDate = installmentDate;
		this.expectedAmt = expectedAmt;
		this.loanAcNo = loanAcNo;
		this.delayReason = delayReason;
	}

	@Ignore
	public Installment(final int installmentId, final Date installmentDate,
	                   final BigDecimal expectedAmt, final int loanAcNo) {
		this.installmentId = installmentId;
		this.installmentDate = installmentDate;
		this.expectedAmt = expectedAmt;
		this.loanAcNo = loanAcNo;
	}

	@Override
	public String toString() {
		return "Installment{" +
				"loan=" + loan +
				", installmentId=" + installmentId +
				", installmentDate=" + installmentDate +
				", expectedAmt=" + expectedAmt.toPlainString() +
				", loanAcNo=" + loanAcNo +
				", delayReason='" + delayReason + '\'' +
				'}';
	}

	public String getDelayReason() {
		return delayReason;
	}

	public void setDelayReason(final String delayReason) {
		this.delayReason = delayReason;
	}

	public int getInstallmentId() {
		return installmentId;
	}

	public void setInstallmentId(final int installmentId) {
		this.installmentId = installmentId;
	}

	public Date getInstallmentDate() {
		return installmentDate;
	}

	public void setInstallmentDate(final Date installmentDate) {
		this.installmentDate = installmentDate;
	}

	public BigDecimal getExpectedAmt() {
		return expectedAmt;
	}

	public void setExpectedAmt(final BigDecimal expectedAmt) {
		this.expectedAmt = expectedAmt;
	}

	public int getLoanAcNo() {
		return loanAcNo;
	}

	public void setLoanAcNo(final int loanAcNo) {
		this.loanAcNo = loanAcNo;
	}


	public Loan getLoan() {
		return loan;
	}

	public void setLoan(final Loan loan) {
		this.loan = loan;
	}
}
