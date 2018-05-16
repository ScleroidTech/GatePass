package com.scleroidtech.gatepass.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.scleroidtech.gatepass.data.local.model.Visit;

import java.util.List;

import io.reactivex.Single;

/**
 * Copyright (C)
 *
 * @author Ganesh Kaple
 * @since 3/13/18
 */
@Dao
public interface VisitDao extends BaseDao<Visit> {
    /**
     * Select all Visitors from the Visit table.
     *
     * @return all Visitors.
     */
    @Query("SELECT * FROM Visit")
    List<Visit> getItems();

    /*
     */
/**
     * Select a Visit by id.
     *
     * @param VisitorId the Visit id.
     * @return the Visit with VisitorId.
 *//*

    @Query("SELECT * FROM Visit WHERE visitId = :VisitorId")
    Visit getItem(String VisitorId);
*/
    /*

     */
/**
     * Insert a Visit in the database. If the Visit already exists, replace it.
     *
     * @param Visit the Visit to be inserted.
 *//*

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Visit Visit);

    */

    /**
     * Update a Visit.
     *
     * @param Visit Visit to be updated
     * @return the number of Visit updated. This should always be 1.
     *//*

    @Override
    @Update
    int update(Visit Visit);
*/
    @Override
    @Query("SELECT * FROM Visit")
    LiveData<List<Visit>> getItemsLive();

    @Override
    @Query("SELECT * FROM Visit WHERE visitId = :serialNo")
    Visit getItem(long serialNo);

    @Override
    @Query("SELECT * FROM Visit WHERE visitId = :serialNo")
    Single<Visit> getRxItem(int serialNo);

    @Override
    @Query("SELECT * FROM Visit WHERE visitId = :serialNo")
    LiveData<Visit> getItemLive(int serialNo);

    @Override
    @Query("SELECT COUNT(*) from Visit")
    int countItem();

    /*  @Override
	  long[] insertAll(List<Visit> visits);

	  @Override
	  @Delete
	  void delete(Visit visit);
  */
    @Override
    @Query("DELETE FROM Visit")
    void nukeTable();

    /**
     * Update the complete status of a Visit
     *
     * @param VisitorId id of the Visit
     * @param exitTime  status to be updated
     */
    @Query("UPDATE Visit SET exitTime = :exitTime WHERE visitId = :VisitorId")
    void updateCompleted(int VisitorId, String exitTime);

    /**
     * Delete a Visit by id.
     *
     * @return the number of Visit deleted. This should always be 1.
     */
    @Query("DELETE FROM Visit WHERE visitid = :VisitId")
    int delete(int VisitId);


}
