package com.scleroidtech.gatepass.di;

import com.scleroidtech.gatepass.utils.ui.ActivityUtils;
import com.scleroidtech.gatepass.utils.ui.DateUtils;
import com.scleroidtech.gatepass.utils.ui.SnackBarUtils;
import com.scleroidtech.gatepass.utils.ui.TextViewUtils;
import com.scleroidtech.gatepass.utils.ui.ToastUtils;

import javax.inject.Singleton;

import dagger.Module;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/6/18
 */
@Module
public abstract class UtilsModule {

	@Singleton
	abstract DateUtils getDateUtils();

	@Singleton
	abstract TextViewUtils getTextViewUtils();

	@Singleton
	abstract ActivityUtils getActivityUtils();

	@Singleton
	abstract SnackBarUtils getSnackBarUtils();

	@Singleton
	abstract ToastUtils getToastUtils();



}
