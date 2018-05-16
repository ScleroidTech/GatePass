package com.scleroidtech.gatepass.di;


import com.scleroidtech.gatepass.fragments.RegisterCustomerFragment;
import com.scleroidtech.gatepass.fragments.RegisterLoanFragment;
import com.scleroidtech.gatepass.fragments.ReminderFragment;
import com.scleroidtech.gatepass.fragments.customer.CustomerFragment;
import com.scleroidtech.gatepass.fragments.dashboard.DashboardFragment;
import com.scleroidtech.gatepass.fragments.dialogs.DatePickerDialogFragment;
import com.scleroidtech.gatepass.fragments.dialogs.DelayDialogFragment;
import com.scleroidtech.gatepass.fragments.dialogs.InsertExpenseDialogFragment;
import com.scleroidtech.gatepass.fragments.dialogs.RegisterReceivedDialogFragment;
import com.scleroidtech.gatepass.fragments.expense.ExpenseFragment;
import com.scleroidtech.gatepass.fragments.loanDetails.LoanDetailsFragment;
import com.scleroidtech.gatepass.fragments.passbook.PassbookFragment;
import com.scleroidtech.gatepass.fragments.people.PeopleFragment;
import com.scleroidtech.gatepass.fragments.report.ReportFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/6/18
 */
@Module
public abstract class FragmentBuildersModule {
	//TODO Update this class
	@ContributesAndroidInjector
	abstract DashboardFragment contributeDashboardFragment();

	@ContributesAndroidInjector
	abstract CustomerFragment contributeCustomerFragment();

	@ContributesAndroidInjector
	abstract ExpenseFragment contributeExpenseFragment();

	@ContributesAndroidInjector
	abstract LoanDetailsFragment contributeLoanDetailsFragment();

	@ContributesAndroidInjector
	abstract PassbookFragment contributePassbookFragment();

	@ContributesAndroidInjector
	abstract PeopleFragment contributePeopleFragment();

	@ContributesAndroidInjector
	abstract RegisterCustomerFragment contributeRegisterCustomerFragment();

	@ContributesAndroidInjector
	abstract RegisterLoanFragment contributeRegisterMoneyFragment();

	@ContributesAndroidInjector
	abstract RegisterReceivedDialogFragment contributeRegisterReceivedFragment();

	@ContributesAndroidInjector
	abstract ReminderFragment contributeReminderFragment();

	@ContributesAndroidInjector
	abstract ReportFragment contributeReportFragment();


	@ContributesAndroidInjector
	abstract DelayDialogFragment contributeDelayDialog();

	@ContributesAndroidInjector
	abstract DatePickerDialogFragment contributeDatePickerDialogFragment();

	@ContributesAndroidInjector
	abstract InsertExpenseDialogFragment
	contributeInserDialogFragment();


}
