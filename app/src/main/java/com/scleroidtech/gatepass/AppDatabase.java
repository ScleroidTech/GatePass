package com.scleroidtech.gatepass;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.scleroidtech.gatepass.model.Person;

/**
 * @author Ganesh Kaple
 * @see com.scleroidtech.gatepass.model.Person
 * @since 27/10/17
 * It is a singleton class,so it holds only one object for it's entire existance
 * It holds the current object of database
 * It handles creating of the database if it doesn't exists & providing the database object whenever required
 */
@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
 /*TODO Remove if DI Worked   *//**
     * Holds the instance of the database
     *//*

    private static AppDatabase instance;

    *//**
     * Returns the instance of AppDatabase class, creates a new one if doesn't exists,
     * & returns that
     *
     * @param context Context of Application or current activity needs to be passed
     * @return AppDatabase returns the instance of Appdatabase
     *//*
    public static AppDatabase getAppDatabase(Context context) {
        *//*
           creates a new database if instance doesn't exists
         *//*

        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "nemai-database")
                            //While Migration of database, it destroys previous versions, should be removed
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }

    *//**
     * Destroys the instance of the database, doesn't actually destroy the database, just the pointer to it,
     *//*
    public static void destroyInstance() {

        instance = null;
    }
*/

    /**
     * Parcel Model Data Access Object,
     * For Room Library
     *
     * @return an object of
     * @see com.scleroidtech.gatepass.Data.local.Dao.PersonDao
     * @see com.scleroidtech.gatepass.Data.local.Dao.PersonDao
     */
    public abstract PersonDao personDao();

    /**
     * Parcel Model Data Access Object,
     * For Room Library
     *
     * @return an object of
     * @see com.scleroidtech.gatepass.Data.local.Dao.VisitorDao
     * @see com.scleroidtech.gatepass.Data.local.Dao.VisitorDao
     */
    public abstract VisitorDao visitorDao();


}
