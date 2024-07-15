package com.example.ecommerceapps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ecommerceapps.model.Account;
import com.example.ecommerceapps.model.Product;
import com.example.ecommerceapps.repository.EcommRepository;

import java.util.List;

import retrofit2.Call;


public class ViewModal extends AndroidViewModel {

    private EcommRepository repository;
    private LiveData<List<Product>> allFlowers;
    private LiveData<List<Product>> allFlowersItems;
    private LiveData<List<Account>> accountItems;

    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new EcommRepository(application);
        allFlowers = repository.getAllProducts();
        allFlowersItems = repository.getAllFlowersItems();
        accountItems = repository.getAllAccount();
    }

    public void insert(Product product) {
        repository.insert(product);
    }

    public LiveData<List<Product>> getAllProducts() {
        return allFlowers;
    }

    public LiveData<List<Account>> getAllAccount() {
        return accountItems;
    }

    public LiveData<List<Product>> getAllFlowersItems() {
        return allFlowersItems;
    }

    public void getAccountDetails(Account account) {
        repository.insertAccount(account);
    }

    public void delete(Account account) {
        repository.delete(account);
    }

}
