package com.scleroidtech.gatepass.data.local;

/**
 *
 */

import android.support.annotation.NonNull;

import com.scleroidtech.gatepass.AppExecutors;
import com.scleroidtech.gatepass.data.VisitorsDataSource;
import com.scleroidtech.gatepass.data.local.dao.VisitorDao;
import com.scleroidtech.gatepass.data.local.model.Visit;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Copyright (C)
 *
 * @author Ganesh Kaple
 * @since 3/13/18
 * Concrete implementation of a data source as a db.
 */
@Singleton
public class VisitorLocalDataSource implements VisitorsDataSource {
    private final VisitorDao visitorsDao;

    private final AppExecutors mAppExecutors;

    @Inject
    public VisitorLocalDataSource(VisitorDao visitorsDao, AppExecutors mAppExecutors) {
        this.visitorsDao = visitorsDao;
        this.mAppExecutors = mAppExecutors;
    }

    /**
     * Note: {@link VisitorsDataSource.LoadVisitorsCallback#onDataNotAvailable()} is fired if the database doesn't exist
     * or the table is empty.
     */
    @Override
    public void getVisitors(@NonNull final VisitorsDataSource.LoadVisitorsCallback callback) {
        Runnable runnable = () -> {
            final List<Visit> visits = visitorsDao.getVisitors();
            mAppExecutors.mainThread().execute(() -> {
                if (visits.isEmpty()) {
                    // This will be called if the table is new or just empty.
                    callback.onDataNotAvailable();
                } else {
                    callback.onVisitorsLoaded(visits);
                }
            });
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getVisitor(@NonNull String VisitorId, @NonNull GetVisitorCallback callback) {

    }

    @Override
    public void saveVisitor(@NonNull Visit Visit) {

    }

    @Override
    public void exitVisitor(@NonNull Visit Visit) {

    }

    @Override
    public void exitVisitor(@NonNull String VisitorId) {

    }


    @Override
    public void activateVisitor(@NonNull Visit Visit) {

    }

    @Override
    public void activateVisitor(@NonNull String VisitorId) {

    }

    @Override
    public void clearCompletedVisitors() {

    }

    @Override
    public void refreshVisitors() {

    }

    @Override
    public void deleteAllVisitors() {

    }

    @Override
    public void deleteVisitor(@NonNull String VisitorId) {

    }
}
