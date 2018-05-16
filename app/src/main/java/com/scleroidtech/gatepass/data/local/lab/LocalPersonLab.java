package com.scleroidtech.gatepass.data.local.lab;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.scleroidtech.gatepass.data.local.AppDatabase;
import com.scleroidtech.gatepass.data.local.LocalDataSource;
import com.scleroidtech.gatepass.data.local.dao.PersonDao;
import com.scleroidtech.gatepass.data.local.model.Person;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Copyright (C) 2018
 *
 * @author Ganesh Kaple
 * @since 4/5/18
 */
public class LocalPersonLab implements LocalDataSource<Person> {
	private final PersonDao personDao;


	@Inject
	LocalPersonLab(final AppDatabase appDatabase) {
		this.personDao = appDatabase.personDao();
	}

	/**
	 * gets a list of all items
	 */
	@Override
	public LiveData<List<Person>> getItems() {
        /* Alternate Method for same purpose
        Runnable runnable = () -> {
            final LiveData<List<Person>> persons= personDao.getAllPersonLive();
            appExecutors.mainThread().execute(() -> {
                if (persons.getValue().isEmpty()){
                    callback.onDataNotAvailable();
                }
                else callback.onLoaded(persons);
            });


        };
        appExecutors.diskIO().execute(runnable);*/

		Timber.d("getting all persons");
		return personDao.getItemsLive();
	}

	/**
	 * gets a single item provided by id
	 *
	 * @param itemId the id of the item to be get
	 */
	@Override
	public LiveData<Person> getItem(final int itemId) {
		Timber.d("getting person with id %d", itemId);
		return personDao.getItemLive(itemId);
	}

	/**
	 * Saves item to data source
	 *
	 * @param item item object to be saved
	 */
	@Override
	public Single<Person> saveItem(@NonNull final Person item) {
		Timber.d("creating new person ");

		return Single.fromCallable(() -> {
			long rowId = personDao.insert(item);
			Timber.d("person stored " + rowId);
			return item;
		}).subscribeOn(Schedulers.io());
	}


	/**
	 * adds a list of objects to the data source
	 *
	 * @param items list of items
	 */
	@Override
	public Completable addItems(@NonNull final List<Person> items) {
		Timber.d("creating new person ");

		return Completable.fromRunnable(() -> {
			long[] rowId = personDao.insertAll(items);
			Timber.d("person stored " + rowId.length);

		}).subscribeOn(Schedulers.io());
	}

	/**
	 * refreshes the data source
	 */
	@Override
	public void refreshItems() {

	}

	/**
	 * Deletes all the data source
	 */
	@Override
	public Completable deleteAllItems() {
		Timber.d("Deleting all persons");
		return Completable.fromRunnable(() -> personDao.nukeTable()).subscribeOn(Schedulers.io
				());

	}

	/**
	 * deletes a single item from the database
	 *
	 * @param item item to be deleted
	 */
	@Override
	public Completable deleteItem(@NonNull final Person item) {
		Timber.d("deleting person with id %d", item.getSerialNo());

		return Completable.fromRunnable(() -> personDao.delete(item))
				.subscribeOn(Schedulers.io());
	}

	@Override
	public Single<Person> updateItem(final Person person) {
		return null;
	}

	/**
	 * gets a single item provided by id
	 *
	 * @param itemId the id of the item to be get
	 */

	public Single<Person> getRxItem(final int itemId) {
		Timber.d("getting person with id %d", itemId);
		return personDao.getRxItem(itemId);
	}

	/*public LiveData<List<Person>> getPersonsWithLoans() {
		LiveData<List<Person>> personLiveData = personDao.getItemsLive();

		// TODO Test this, if works remove below code, this part has performance issues
		personLiveData = Transformations.switchMap(personLiveData, inputPersons -> {
			MediatorLiveData<List<Person>> personMediatorLiveData = new MediatorLiveData<>();

			for (Person person : inputPersons) {

				personMediatorLiveData.addSource(
						loanDao.getLoanByPersonIdLive(person.getSerialNo()), loan -> {

							person.setLoans(loan);
							personMediatorLiveData.postValue(inputPersons);

						});
			}
			return personMediatorLiveData;
		});
		return personLiveData;
       *//* personLiveData = Transformations.map(personLiveData, inputStates -> {
            for (Person state : inputStates) {
                state.setLoans(loanDao.getLoans(state.getPersonId()));
            }
            return inputStates;
        });
        return personLiveData;*//*
	}
*/
/*
	public LiveData<Person> getPerson(int id) {
		LiveData<Person> personLiveData = personDao.getItemsLive(id);
		personLiveData = Transformations.switchMap(personLiveData, inputPerson -> {
			MediatorLiveData<Person> mediatorLiveData = new MediatorLiveData<>();
			//List<Loan> loans = new ArrayList<>();
			mediatorLiveData.addSource(loanDao.getLoanByPersonIdLive(inputPerson.getPersonId
							()),
					inputPerson::setLoans);

			return mediatorLiveData;
		});
		return personLiveData;
		//Good Job buddy, now the real challenge is next method
	}
*/


}
