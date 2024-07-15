package com.example.ecommerceapps.itemfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ecommerceapps.adpater.ProductAdapter;
import com.example.ecommerceapps.adpater.ProductCategoryAdapter;
import com.example.ecommerceapps.databinding.ActivityHomeBinding;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.interfaces.FragmentNavigation;
import com.example.ecommerceapps.loginfragment.LoginFragment;

public class SpecifyItemFragment extends Fragment {


    ProductAdapter adapter;
    ProductCategoryAdapter productCategoryAdapter;
    ActivityHomeBinding binding;
    FragmentBottomNavigation communicator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            communicator = (FragmentBottomNavigation) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentToActivityCommunicator");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityHomeBinding.inflate(inflater, container, false);
        communicator.navigateBottomFrag(3,true);



        binding.logout.setOnClickListener(v -> {
            FragmentNavigation navRegister = (FragmentNavigation) getActivity();
            if (navRegister != null) {
                navRegister.navigateFrag(new LoginFragment(), false);
            }
        });

        return binding.getRoot();

    }
}
