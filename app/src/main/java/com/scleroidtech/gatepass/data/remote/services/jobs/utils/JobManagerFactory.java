package com.scleroidtech.gatepass.data.remote.services.jobs.utils;

import android.content.Context;
import android.os.Build;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;

import timber.log.Timber;

public class JobManagerFactory {

	private static JobManager jobManager;
	private static CustomLogger customLogger = new CustomLogger() {

		@Override
		public boolean isDebugEnabled() {
			return true;
		}

		@Override
		public void d(String text, Object... args) {
			Timber.d(String.format(text, args));
		}

		@Override
		public void e(Throwable t, String text, Object... args) {
			Timber.e(t, String.format(text, args));
		}

		@Override
		public void e(String text, Object... args) {
			Timber.e(String.format(text, args));
		}

		@Override
		public void v(String text, Object... args) {
			// no-op
		}
	};

	public static synchronized JobManager getJobManager() {
		return jobManager;
	}

	public static synchronized JobManager getJobManager(Context context) {
		if (jobManager == null) {
			jobManager = configureJobManager(context);

		}
		return jobManager;
	}

	private static JobManager configureJobManager(Context context) {

	 /*   DependencyInjector dependencyInjector = job -> {
		    // this line depends on how your Dagger components are setup;
		    // the important part is to cast job to BaseJob
		    ((Application) context).DaggerAppComponent().inject((BaseJob) job);
	    };
*/
		Configuration.Builder builder = new Configuration.Builder(context)
				.minConsumerCount(1)//always keep at least one consumer alive
				.maxConsumerCount(3)//up to 3 consumers at a time
				.loadFactor(3)//3 jobs per consumer
				.consumerKeepAlive(120)//wait 2 minutes
				//      .injector(dependencyInjector)
				.customLogger(customLogger);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			builder.scheduler(FrameworkJobSchedulerService.createSchedulerFor(context,
					SchedulerJobService.class), true);
		} else {
			//TODO GCM not supported any more, what to be done?
           /* int enableGcm = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable
           (context);
            if (enableGcm == ConnectionResult.SUCCESS) {
                builder.scheduler(GcmJobSchedulerService.createSchedulerFor(context,
                        GcmJobSchedulerService.class), true);
            }*/
		}
		return new JobManager(builder.build());
	}
}
