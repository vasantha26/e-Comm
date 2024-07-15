package com.example.ecommerceapps.bottomfragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ecommerceapps.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {


    FragmentMenuBinding binding;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater ,container,false);



        return binding.getRoot();
    }



}
