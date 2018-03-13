package com.scleroidtech.gatepass.Data.local.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.scleroidtech.gatepass.model.Person;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


/**
 * Data Access Object required for
 *
 * @author Ganesh Kaple
 * @see android.arch.persistence.room.Room
 * For Model
 * @see com.scleroidtech.gatepass.model.Person
 * @since 22-11-2017
 */
public interface PersonDao {


    /**
     * Select Query
     *
     * @return List of all Persones in database
     */
    @Query("SELECT * FROM Person")
    List<Person> getPersons();

    /**
     * Returns  list of all Persones
     *
     * @return LiveData object List of all Persones in database
     */
    @Query("SELECT * FROM Person")
    LiveData<List<Person>> getAllPersonLive();

    /**
     * Returns a specific value compared to serialNo passed
     *
     * @param serialNo the serialNo of object to be found
     * @return Person object with same serialNo
     */
    @Query("SELECT * FROM Person where serialNo = :serialNo ")
    Person findById(long serialNo);

    /**
     * select query to count Number of Person
     *
     * @return number of total entries in the table
     */
    @Query("SELECT COUNT(*) from Person")
    int countPerson();

    /**
     * Performs insertion operation
     *
     * @param Person inserts this object in the database
     */
    @Insert(onConflict = REPLACE)
    void insert(Person Person);

    /**
     * Performs insertion operation for multiple values
     *
     * @param Person inserts list of Person object
     */
    @Insert
    void insertAll(Person... Person);

    /**
     * Updates a specified dataset
     *
     * @param Person the Person which needs to be updated
     */
    @Update(onConflict = REPLACE)
    void update(Person Person);


    @Delete
    void delete(Person Person);

    /**
     * Let the database be a part of history
     * I meant, it deletes the whole table
     */
    @Query("DELETE FROM Person")
    void nukeTable();

}

