package com.scleroidtech.gatepass.Data.local.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.scleroidtech.gatepass.model.Visitor;

import java.util.List;

/**
 * Copyright (C)
 *
 * @author Ganesh Kaple
 * @since 3/13/18
 */
@Dao
public interface VisitorDao {
    /**
     * Select all Visitors from the Visitors table.
     *
     * @return all Visitors.
     */
    @Query("SELECT * FROM Visitors")
    List<Visitor> getVisitors();

    /**
     * Select a Visitor by id.
     *
     * @param VisitorId the Visitor id.
     * @return the Visitor with VisitorId.
     */
    @Query("SELECT * FROM Visitors WHERE entryid = :VisitorId")
    Visitor getVisitorById(String VisitorId);

    /**
     * Insert a Visitor in the database. If the Visitor already exists, replace it.
     *
     * @param Visitor the Visitor to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVisitor(Visitor Visitor);

    /**
     * Update a Visitor.
     *
     * @param Visitor Visitor to be updated
     * @return the number of Visitors updated. This should always be 1.
     */
    @Update
    int updateVisitor(Visitor Visitor);

    /**
     * Update the complete status of a Visitor
     *
     * @param VisitorId id of the Visitor
     * @param exitTime  status to be updated
     */
    @Query("UPDATE Visitors SET exitTime = :exitTime WHERE entryid = :VisitorId")
    void updateCompleted(String VisitorId, String exitTime);

    /**
     * Delete a Visitor by id.
     *
     * @return the number of Visitors deleted. This should always be 1.
     */
    @Query("DELETE FROM Visitors WHERE visitid = :VisitId")
    int deleteVisitorById(String VisitId);

    /**
     * Delete all Visitors.
     */
    @Query("DELETE FROM Visitors")
    void deleteVisitors();

    /**
     * Delete all completed Visitors from the table.
     *
     * @return the number of Visitors deleted.
     */
    @Query("DELETE FROM Visitors WHERE completed = 1")
    int deleteCompletedVisitors();
}
