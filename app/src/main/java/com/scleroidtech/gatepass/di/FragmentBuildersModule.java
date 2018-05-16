package com.scleroidtech.gatepass.di;


import android.arch.lifecycle.ReportFragment;

import com.scleroidtech.gatepass.fragments.HomeFragment;
import com.scleroidtech.gatepass.fragments.NewVisitFragment;
import com.scleroidtech.gatepass.fragments.dialogs.DatePickerDialogFragment;
import com.scleroidtech.gatepass.fragments.dialogs.TimePickerDialogFragment;

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
	abstract ReportFragment contributeReportFragment();


	@ContributesAndroidInjector
	abstract TimePickerDialogFragment contributeTimePickerDialog();

	@ContributesAndroidInjector
	abstract DatePickerDialogFragment contributeDatePickerDialogFragment();

	@ContributesAndroidInjector
	abstract HomeFragment
	contributeHomeFragment();

	@ContributesAndroidInjector
	abstract NewVisitFragment
	contributeNewVisitFragment();


}
