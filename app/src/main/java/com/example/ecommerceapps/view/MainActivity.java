package com.example.ecommerceapps.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapps.bottomfragment.MenuFragment;
import com.example.ecommerceapps.bottomfragment.MoreFragment;
import com.example.ecommerceapps.bottomfragment.ProfileFragment;
import com.example.ecommerceapps.databinding.ActivityMainBinding;
import com.example.ecommerceapps.bottomfragment.CartFragment;
import com.example.ecommerceapps.bottomfragment.HomeFragment;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.interfaces.FragmentNavigation;
import com.example.ecommerceapps.R;
import com.example.ecommerceapps.loginfragment.LoginFragment;
import com.example.ecommerceapps.model.Account;
import com.example.ecommerceapps.viewmodel.ViewModal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentNavigation, FragmentBottomNavigation {

    FirebaseAuth fAuth;

    ActivityMainBinding binding;

    ViewModal viewModal;

    List<Account> accounts = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        fAuth = FirebaseAuth.getInstance();

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        viewModal = new ViewModelProvider(this).get(ViewModal.class);


        FirebaseUser currentUser = fAuth.getCurrentUser();

        viewModal.getAllAccount().observe(this, account -> this.accounts = account);


        if (currentUser != null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new HomeFragment()).addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new LoginFragment()).commit();
        }

    }

    @Override
    public void navigateFrag(Fragment fragment, boolean addToStack) {
        androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment);
        if (addToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }


    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;

        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.navigation_you) {
            selectedFragment = new ProfileFragment();
        } else if (itemId == R.id.navigation_more) {
            selectedFragment = new MoreFragment();
        } else if (itemId == R.id.navigation_cart) {
            selectedFragment = new CartFragment();
        } else if (itemId == R.id.navigation_menu) {
            selectedFragment = new MenuFragment();        }
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
        }
        return true;
    };

    @Override
    public void navigateBottomFrag( int fragment , boolean addToStack) {
        if (fragment == 1 && addToStack) {
            binding.bottomNavigationView.setVisibility(View.GONE);
        }else  if (fragment == 2 && addToStack){
            binding.bottomNavigationView.setVisibility(View.GONE);
        }else  if (fragment == 3 && addToStack){
            binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }

    }
}
