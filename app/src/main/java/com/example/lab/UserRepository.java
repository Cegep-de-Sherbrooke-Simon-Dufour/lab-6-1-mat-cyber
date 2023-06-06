package com.example.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {
    @Inject
    public UserRepository(){}
    private ArrayList<User> users =
            new ArrayList<User>( Arrays.asList(
            new User("Mathieu", "blabla@gmail.com"),
            new User("Bob", "bob@bob.com"),
            new User("Alice", "alice@alice")));

    public void addUser(User user){
        users.add(user);
    }

    public void deleteUser(User user){
        users.remove(user);
    }

    public List<User> getUsers(){
        return users;
    }
}
