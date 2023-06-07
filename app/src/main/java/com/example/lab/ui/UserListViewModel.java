package com.example.lab.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab.model.User;
import com.example.lab.model.UserRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserListViewModel extends ViewModel {
    UserRepository userRepository;
    @Inject
    public UserListViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.addUser(user);
    }

    public void removeUser(User user){
        userRepository.deleteUser(user);
    }

    public LiveData<List<User>> getUsers(){
        return userRepository.getUsersLiveData();
    }
}
