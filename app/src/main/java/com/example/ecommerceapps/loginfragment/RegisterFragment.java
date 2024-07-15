package com.example.ecommerceapps.loginfragment;


import android.content.Context;
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
import com.example.ecommerceapps.databinding.FragmentRegisterBinding;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.interfaces.FragmentNavigation;
import com.example.ecommerceapps.R;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends Fragment {


    private FirebaseAuth fAuth;

    FragmentRegisterBinding binding;

    private FragmentBottomNavigation communicator;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            communicator = (FragmentBottomNavigation) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement FragmentToActivityCommunicator");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater ,container ,false);

        communicator.navigateBottomFrag(2,true);


        fAuth = FirebaseAuth.getInstance();

        binding.lgbtn.setOnClickListener(v -> {
            FragmentNavigation navHome = (FragmentNavigation) getActivity();
            if (navHome != null) {
                navHome.navigateFrag(new LoginFragment(), true);
            }
        });

        binding.btnRegisterReg.setOnClickListener(v -> validateEmptyForm());

        return binding.getRoot();
    }

    private void firebaseSignUp() {
        fAuth.createUserWithEmailAndPassword(binding.regUsername.getText().toString(), binding.regPassword.getText().toString())
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        FragmentNavigation navHome = (FragmentNavigation) getActivity();
                        if (navHome != null) {
                            navHome.navigateFrag(new LoginFragment(), true);
                        }
                    } else {
                        binding.btnRegisterReg.setEnabled(true);
                        binding.btnRegisterReg.setAlpha(1.0f);
                        Toast.makeText(getContext(), task.getException() != null ? task.getException().getMessage() : "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void validateEmptyForm() {
        Drawable icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_warning);
        if (icon != null) {
            icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        }

        if (TextUtils.isEmpty(binding.regUsername.getText().toString().trim())) {
            binding.regUsername.setError("Please Enter Username", icon);
        } else if (TextUtils.isEmpty(binding.regPassword.getText().toString().trim())) {
            binding.regPassword.setError("Please Enter Password", icon);
        } else if (TextUtils.isEmpty(binding.regCnfPassword.getText().toString().trim())) {
            binding.regCnfPassword.setError("Please Enter Password Again", icon);
        } else if (binding.regUsername.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            if (binding.regPassword.getText().toString().length() >= 5) {
                if (binding.regPassword.getText().toString().equals(binding.regCnfPassword.getText().toString())) {
                    firebaseSignUp();
                } else {
                    binding.regCnfPassword.setError("Password didn't match", icon);
                }
            } else {
                binding.regPassword.setError("Please Enter at least 5 characters", icon);
            }
        } else {
            binding.regUsername.setError("Please Enter Valid Email Id", icon);
        }
    }
}
