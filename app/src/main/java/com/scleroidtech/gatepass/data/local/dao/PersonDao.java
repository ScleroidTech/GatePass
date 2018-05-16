package com.scleroidtech.gatepass.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.scleroidtech.gatepass.data.local.model.Person;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


/**
 * Data Access Object required for
 *
 * @author Ganesh Kaple
 * @see android.arch.persistence.room.Room
 * For Model
 * @see Person
 * @since 22-11-2017
 */
@Dao
public interface PersonDao extends BaseDao<Person> {


    /**
     * Select Query
     *
     * @return List of all Persones in database
     */
    @Override
    @Query("SELECT * FROM Person")
    List<Person> getItems();

    /**
     * Returns  list of all Persones
     *
     * @return LiveData object List of all Persones in database
     */
    @Override
    @Query("SELECT * FROM Person")
    LiveData<List<Person>> getItemsLive();

    /**
     * Returns a specific value compared to serialNo passed
     *
     * @param serialNo the serialNo of object to be found
     * @return Person object with same serialNo
     */
    @Override
    @Query("SELECT * FROM Person where serialNo = :serialNo ")
    Person getItem(long serialNo);

    /**
     * select query to count Number of Person
     *
     * @return number of total entries in the table
     */
    @Override
    @Query("SELECT COUNT(*) from Person")
    int countItem();

    /**
     * Performs insertion operation
     *
     * @param Person inserts this object in the database
     */
    @Override
    @Insert(onConflict = REPLACE)
    void insert(Person Person);

    /**
     * Performs insertion operation for multiple values
     *
     * @param Person inserts list of Person object
     */
    @Override
    @Insert
    void insertAll(Person... Person);

    /**
     * Updates a specified dataset
     *
     * @param Person the Person which needs to be updated
     */
    @Override
    @Update(onConflict = REPLACE)
    int update(Person Person);


    @Override
    @Delete
    void delete(Person Person);

    /**
     * Let the database be a part of history
     * I meant, it deletes the whole table
     */
    @Override
    @Query("DELETE FROM Person")
    void nukeTable();

}

