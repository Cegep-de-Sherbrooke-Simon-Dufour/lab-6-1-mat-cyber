package com.example.lab.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab.model.RentalItem;
import com.example.lab.model.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RentalItemListViewModel extends ViewModel {
    Repository repository;

    @Inject
    public RentalItemListViewModel(Repository repository){
        this.repository = repository;
    }

    public void addRentalItem(RentalItem rentalItem){
        repository.addRentalItem(rentalItem);
    }

    public void removeRentalItem(RentalItem rentalItem){
        repository.deleteRentalItem(rentalItem);
    }

    public LiveData<List<RentalItem>> getRentalItems(int userId){
        return repository.getRentalItemsLiveData(userId);
    }
}
