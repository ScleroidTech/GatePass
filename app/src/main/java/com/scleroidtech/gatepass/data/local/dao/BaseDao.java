package com.scleroidtech.gatepass.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

public interface BaseDao<T> {

	/**
	 * Select Query
	 *
	 * @return List of all Items in database
	 */
	List<T> getItems();

	/**
	 * Returns  list of all Items
	 *
	 * @return LiveData object List of all Items in database
	 */

	LiveData<List<T>> getItemsLive();

	/**
	 * Returns a specific value compared to serialNo passed
	 *
	 * @param serialNo the serialNo of object to be found
	 * @return Person object with same serialNo
	 */

	T getItem(long serialNo);

	/**
	 * select query to count Number of Items
	 *
	 * @return number of total entries in the table
	 */

	int countItem();

	/**
	 * Performs insertion operation
	 *
	 * @param t inserts this object in the database
	 */
	@Insert(onConflict = REPLACE)
	void insert(T t);

	/**
	 * Performs insertion operation for multiple values
	 *
	 * @param ts inserts list of Person object
	 */
	@Insert
	void insertAll(T... ts);

	/**
	 * Updates a specified dataset
	 *
	 * @param t the item which needs to be updated
	 */
	@Update(onConflict = REPLACE)
	int update(T t);


	@Delete
	void delete(T t);

	/**
	 * Let the database be a part of history
	 * I meant, it deletes the whole table
	 */
	void nukeTable();

}
