package com.example.lab.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab.model.Repository;
import com.example.lab.model.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserListViewModel extends ViewModel {
    Repository repository;
    @Inject
    public UserListViewModel(Repository repository){
        this.repository = repository;
    }

    public void addUser(User user){
        repository.addUser(user);
    }

    public void removeUser(User user){
        repository.deleteUser(user);
    }

    public LiveData<List<User>> getUsers(){
        return repository.getUsersLiveData();
    }
}
