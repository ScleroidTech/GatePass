/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.scleroidtech.gatepass.data;

import android.support.annotation.NonNull;

import com.scleroidtech.gatepass.model.Visit;

import java.util.List;

/**
 * Main entry point for accessing Visitors data.
 * <p>
 * For simplicity, only getVisitors() and getVisitor() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new Visit is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 */
public interface VisitorsDataSource {

    void getVisitors(@NonNull LoadVisitorsCallback callback);

    void getVisitor(@NonNull String VisitorId, @NonNull GetVisitorCallback callback);

    void saveVisitor(@NonNull Visit Visit);

    void exitVisitor(@NonNull Visit Visit);

    void exitVisitor(@NonNull String VisitorId);

    void activateVisitor(@NonNull Visit Visit);

    void activateVisitor(@NonNull String VisitorId);

    void clearCompletedVisitors();

    void refreshVisitors();

    void deleteAllVisitors();

    void deleteVisitor(@NonNull String VisitorId);

    interface LoadVisitorsCallback {

        void onVisitorsLoaded(List<Visit> visits);

        void onDataNotAvailable();
    }

    interface GetVisitorCallback {

        void onVisitorLoaded(Visit Visit);

        void onDataNotAvailable();
    }
}
