package com.example.ecommerceapps.loginfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapps.databinding.FragmentLoginBinding;
import com.example.ecommerceapps.bottomfragment.HomeFragment;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.interfaces.FragmentNavigation;
import com.example.ecommerceapps.R;
import com.example.ecommerceapps.model.Account;
import com.example.ecommerceapps.viewmodel.ViewModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

public class LoginFragment extends Fragment   {


    private FirebaseAuth fAuth;

    FragmentLoginBinding binding;

    private FragmentBottomNavigation communicator;

    ViewModal viewModal;




    @Override
    public void onAttach(Context context) {
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
        binding =FragmentLoginBinding.inflate(inflater ,container ,false);

        communicator.navigateBottomFrag(1,true);


        fAuth = FirebaseAuth.getInstance();

        viewModal = new ViewModelProvider(this).get(ViewModal.class);


        binding.btnRegister.setOnClickListener(v -> {
            FragmentNavigation navRegister = (FragmentNavigation) getActivity();
            if (navRegister != null) {
                navRegister.navigateFrag(new RegisterFragment(), false);
            }
        });


        binding.btnLogin.setOnClickListener(v -> validateForm());

        return binding.getRoot();

    }

    private void firebaseSignIn() {

        fAuth.signInWithEmailAndPassword(binding.logUsername.getText().toString(), binding.logPassword.getText().toString())
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        FragmentNavigation navHome = (FragmentNavigation) getActivity();
                        if (navHome != null ) {
                            navHome.navigateFrag(new HomeFragment(), true);
                            Account account = new Account();
                            account.setEmail(binding.logUsername.getText().toString());
                            account.setPassword(binding.logPassword.getText().toString());
                            viewModal.getAccountDetails(account);
                        }
                    } else {
                        binding.btnLogin.setEnabled(true);
                        binding.btnLogin.setAlpha(1.0f);
                        Toast.makeText(getContext(), task.getException() != null ? task.getException().getMessage() : "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void validateForm() {
        Drawable icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_warning);
        if (icon != null) {
            icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        }

        if (TextUtils.isEmpty(binding.logUsername.getText().toString().trim())) {
            binding.logUsername.setError("Please Enter Username", icon);
        } else if (TextUtils.isEmpty(binding.logPassword.getText().toString().trim())) {
            binding.logPassword.setError("Please Enter Password", icon);
        } else if (!binding.logUsername.getText().toString().isEmpty() && !binding.logPassword.getText().toString().isEmpty()) {
            if (binding.logUsername.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                firebaseSignIn();
            } else {
                binding.logUsername.setError("Please Enter Valid Email Id", icon);
            }
        }
    }



}
