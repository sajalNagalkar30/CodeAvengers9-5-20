package spares.matrix.vicky.swapnil.btmnavphery.ui.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import spares.matrix.vicky.swapnil.btmnavphery.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spares.matrix.vicky.swapnil.btmnavphery.ui.activites.MainActivity;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.User;

//com.example.myfirestretrofitapp.activites

public class RegistrationFragment extends Fragment {

    private EditText nameInput1, emailInput1, phoneInput1, passwordInput1;
    Button regBtn;
            TextView logbtn;
    Toolbar toolbar_log1;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_registration, container, false);
        nameInput1 = view.findViewById(R.id.nameInput);
        emailInput1 = view.findViewById(R.id.emailInput);
        phoneInput1 = view.findViewById(R.id.phoneInput);
        passwordInput1 = view.findViewById(R.id.passwordInput2);
logbtn=view.findViewById(R.id.login);
        toolbar_log1=view.findViewById(R.id.toolbarfor_log);
        toolbar_log1.setNavigationIcon(R.drawable.ic_back_main_all);
        toolbar_log1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.fragment_container, new Starting())
                        .addToBackStack(null)
                        .commit();
            }
        });
        regBtn = view.findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                Log.e("reg button", "clicked");


            }
        });
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.fragment_container, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    public void registerUser() {
        String name = nameInput1.getText().toString();
        String email = emailInput1.getText().toString();
        String phone = phoneInput1.getText().toString();
        String password = passwordInput1.getText().toString();

        if (TextUtils.isEmpty(name)){
            MainActivity.appPreference.showToast("Your name is required.");
        } else if (TextUtils.isEmpty(email)){
            MainActivity.appPreference.showToast("Your email is required.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            MainActivity.appPreference.showToast("Invalid email");
        } else if (TextUtils.isEmpty(password)){
            MainActivity.appPreference.showToast("Password required");
        } else if (password.length() < 6){
            MainActivity.appPreference.showToast("Create a password at least 6 characters long.");
        }
        else {
            Call<User> userCall = MainActivity.serviceApi.doRegistration(name, email, phone, password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body().getResponse().equals("inserted")){
                        Log.e("response", response.body().getResponse());
                        nameInput1.setText("");
                        emailInput1.setText("");
                        phoneInput1.setText("");
                        passwordInput1.setText("");
                        MainActivity.appPreference.showToast("Registered Successfully");
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction
                                .replace(R.id.fragment_container, new LoginFragment())
                                .addToBackStack(null)
                                .commit();
                    } else if (response.body().getResponse().equals("exists")){
                        MainActivity.appPreference.showToast("This email already exists");
                    } else if (response.body().getResponse().equals("error")){
                        MainActivity.appPreference.showToast("Oops! something went wrong.");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
        }

    }

}
