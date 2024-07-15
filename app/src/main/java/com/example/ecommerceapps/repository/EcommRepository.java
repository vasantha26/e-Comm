package com.example.ecommerceapps.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.ecommerceapps.dao.AccountDao;
import com.example.ecommerceapps.dao.EcommesDao;
import com.example.ecommerceapps.database.AccountDatabase;
import com.example.ecommerceapps.database.ECommerceDatabase;
import com.example.ecommerceapps.model.Account;
import com.example.ecommerceapps.model.Product;

import java.util.List;

public class EcommRepository {
    private final EcommesDao eCommDao;
    private final AccountDao accountDao;
    private LiveData<List<Product>> allFlowers;
    private LiveData<List<Product>> allFlowersItems;
    private LiveData<List<Account>> accountItems;

    public EcommRepository(Application application) {
        ECommerceDatabase database = ECommerceDatabase.getInstance(application);
        AccountDatabase accountDatabase = AccountDatabase.getInstance(application);
        eCommDao = database.ecommesDao();
        accountDao = accountDatabase.accountDao();
        allFlowers = eCommDao.getAllProducts();
        allFlowersItems = eCommDao.getAllProductsItem();
        accountItems = accountDao.getAllAccount();
    }

    public void insert(Product products) {
        new InsertFlowerAsyncTask(eCommDao).execute(products);
    }


    public LiveData<List<Product>> getAllProducts() {
        return allFlowers;
    }

    public LiveData<List<Product>> getAllFlowersItems() {
        return allFlowersItems;
    }

    public LiveData<List<Account>> getAllAccount() {
        return accountItems;
    }

    public void insertAccount(Account account) {
        new InsertAccountAsyncTask(accountDao).execute(account);
    }

    public void delete(Account account) {
        new DeleteProductAsyncTask(accountDao).execute(account);
    }


    private static class InsertFlowerAsyncTask extends AsyncTask<Product, Void, Void> {
        private EcommesDao eCommDao;

        private InsertFlowerAsyncTask(EcommesDao eCommDao) {
            this.eCommDao = eCommDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            eCommDao.insert(products[0]);
            return null;
        }
    }

    private static class InsertAccountAsyncTask extends AsyncTask<Account, Void, Void> {
        private AccountDao accountDao;

        private InsertAccountAsyncTask(AccountDao accountDao) {
            this.accountDao = accountDao;
        }

        @Override
        protected Void doInBackground(Account... accounts) {
            accountDao.insert(accounts[0]);
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Account, Void, Void> {
        private AccountDao accountDao;

        private DeleteProductAsyncTask(AccountDao productDao) {
            this.accountDao = productDao;
        }

        @Override
        protected Void doInBackground(Account... products) {
            accountDao.delete(products[0]);
            return null;
        }
    }



}
