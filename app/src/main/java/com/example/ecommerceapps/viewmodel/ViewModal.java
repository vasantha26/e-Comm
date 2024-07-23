package com.example.ecommerceapps.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.ecommerceapps.model.Account;
import com.example.ecommerceapps.model.Product;
import com.example.ecommerceapps.repository.EcommRepository;
import java.util.List;


public class ViewModal extends AndroidViewModel {

    EcommRepository repository;
    LiveData<List<Product>> allFlowers;
    LiveData<List<Product>> allFlowersItems;
    LiveData<List<Product>> allCart;
    LiveData<List<Account>> accountItems;

    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new EcommRepository(application);
        allFlowers = repository.getAllProducts();
        allFlowersItems = repository.getAllFlowersItems();
        allCart = repository.getAllCart();
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

    public LiveData<List<Product>> getAllCart() {
        return allCart;
    }

    public void getAccountDetails(Account account) {
        repository.insertAccount(account);
    }

    public void delete(Account account) {
        repository.delete(account);
    }

    public void updateFavorite(int id, boolean isChecked) {
        repository.updateFavorite(id,isChecked);
    }

    public void updateCart(int id, boolean isChecked) {
        repository.updateCart(id,isChecked);
    }

}
