package com.example.lab.model;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Repository {

    private final UserDao userDao;
    private final RentalItemDao rentalItemDao;
    @Inject
    public Repository(AppDatabase database){
        userDao = database.getUserDao();
        rentalItemDao = database.getRentalItemDao();
    }

    public void addUser(User user){
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insertAll(user);
        });
    }

    public void deleteUser(User user){
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.delete(user);
        });
    }

    public LiveData<List<User>> getUsersLiveData(){
        return userDao.getUsers();
    }

    public void addRentalItem(RentalItem rentalItem){
        rentalItemDao.insertAll(rentalItem);
    }

    public void deleteRentalItem(RentalItem rentalItem){
        rentalItemDao.delete(rentalItem);
    }

    public LiveData<List<RentalItem>> getRentalItemsLiveData(int userId){
        return rentalItemDao.getRentals(userId);
    }
}
