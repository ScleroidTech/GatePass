package com.scleroidtech.gatepass.di;

/**
 * Copyright (C) 3/10/18
 * Author ganesh
 */

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scleroidtech.gatepass.data.local.AppDatabase;
import com.scleroidtech.gatepass.data.local.dao.PersonDao;
import com.scleroidtech.gatepass.data.local.dao.VisitDao;
import com.scleroidtech.gatepass.data.repo.LoanRepo;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * This is used by Dagger to inject the required arguments into the {@link }.
 */
@Module(includes = {ViewModelModule.class})
abstract public class RepositoryModule {

    private static final int THREAD_COUNT = 3;

    //TODO When DataBase Added
  /*  @Singleton
    @Binds
    @Local
    abstract TasksDataSource provideTasksLocalDataSource(TasksLocalDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract TasksDataSource provideTasksRemoteDataSource(FakeTasksRemoteDataSource dataSource);

    @Singleton
    @Provides
    static ToDoDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), ToDoDatabase.class, "Tasks.db")
                .build();
    }

    @Singleton
    @Provides
    static TasksDao provideTasksDao(ToDoDatabase db) {
        return db.taskDao();
    }
*/

	//TODO When DataBase Added
  /*  @Singleton
    @Binds
    @Local
    abstract LocalDataSource provideTasksLocalDataSource(LocalLocalDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract LocalDataSource provideTasksRemoteDataSource(FakeTasksRemoteDataSource dataSource);

*/
	@Singleton
	@Provides
	static AppDatabase provideDb(Application context) {

		AppDatabase appDatabase =
				Room.databaseBuilder(context, AppDatabase.class, "financeMatic.db")
						/*TODO
						.addCallback(new RoomDatabase.Callback() {
			   /**
				 * Called when the database is created for the first time.
				 * This is called after all the tables are created.
				 *
				 * @param db The database.

							@Override
							public void onCreate(@NonNull final SupportSQLiteDatabase db) {
								super.onCreate(db);

								//TODO add trigger to update values depending upon operations
								db.execSQL("CREATE TRIGGER");
							}
						})*/
						.fallbackToDestructiveMigration()
						.build();
		Timber.wtf("why we aren't calling this" + appDatabase);
		return appDatabase;
	}

	@Singleton
	@Provides
	static EventBus providesGlobalBus() {
		return EventBus.getDefault();
	}

	@Singleton
	@Provides
	static PersonDao providePersonDao(AppDatabase db) {
		return db.personDao();
	}

	@Singleton
	@Provides
	static VisitDao provideVisitDao(AppDatabase db) {
		return db.visitDao();
	}


   /*TODO remove if not needed
    @Singleton
    @Provides
    static WebService provideWebService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(WebService.class);
    }
*/

  /*  @Singleton
    @Provides
    static RemotePostEndpoint providePostWebService() {
        //return   retrofit.create(RemotePostEndpoint.class);
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //		.addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(RemotePostEndpoint.class);
    }*/
/*
    @Singleton
    @Provides
    static SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
*/

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }
	/*@Singleton
	@Provides
	GcmJobService provideGcmJobService() {
		return new GcmJobService();
	}*/

	@Provides
	@Singleton
	static Gson provideGson() {
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	@Provides
	static public HttpLoggingInterceptor loggingInterceptor() {
		HttpLoggingInterceptor interceptor =
				new HttpLoggingInterceptor(message -> Timber.i(message));
		interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
		return interceptor;
	}

	@Singleton
	abstract LoanRepo provideLoanRepo(AppDatabase db);

}
