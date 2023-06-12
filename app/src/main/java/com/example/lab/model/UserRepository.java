package com.example.lab.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private final UserDao userDao;
    @Inject
    public UserRepository(AppDatabase database){
        userDao = database.getUserDao();
    }
    /*
    private final ArrayList<User> users =
            new ArrayList<User>( Arrays.asList(
            new User("Mathieu", "blabla@gmail.com"),
            new User("Bob", "bob@bob.com"),
            new User("Alice", "alice@alice")));
    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>(users);
    */
    public void addUser(User user){
        //users.add(user);
        //usersLiveData.setValue(users);
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insertAll(user);
        });
    }

    public void deleteUser(User user){
        //users.remove(user);
        //usersLiveData.setValue(users);
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.delete(user);
        });
    }

    public LiveData<List<User>> getUsersLiveData(){
        //return usersLiveData;
        return userDao.getUsers();
    }

}
