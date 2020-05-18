package spares.matrix.vicky.swapnil.btmnavphery.ui.activites;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;


import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.HomeFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.constants.Constant;

import spares.matrix.vicky.swapnil.btmnavphery.ui.fragment.LoginFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.extras.AppPreference;
import spares.matrix.vicky.swapnil.btmnavphery.ui.fragment.ProfileFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.fragment.RegistrationFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.fragment.Starting;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.MyInterface;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.RetrofitClient;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.ServiceApi;

public class MainActivity extends AppCompatActivity implements MyInterface {
    public static AppPreference appPreference;
    public static String c_date;
    FrameLayout container_layout;
    public static ServiceApi serviceApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container_layout = findViewById(R.id.fragment_container);
        appPreference = new AppPreference(this);

        //Log.e("created_at: ", c_date);

        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);

        if (container_layout != null) {
            if (savedInstanceState != null) {
                return;
            }

            //check login status from sharedPreference
            if (appPreference.getLoginStatus()) {
                //when true
                /*getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new ProfileFragment())
                        .commit();*/
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            } else {
                // when get false
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment())
                        .commit();
            }
        }

    } // ending onCreate


    // overridden from MyInterface
    @Override
    public void register() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new RegistrationFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void login(String name, String email, String created_at,String id_user) {
        appPreference.setDisplayName(name);
        appPreference.setDisplayEmail(email);
        appPreference.setCreDate(created_at);
        appPreference.setCRId(id_user);
        homeactivity();

    }

    private void homeactivity() {
        Intent intent=new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void logout() {
        appPreference.setLoginStatus(false);
        appPreference.setDisplayName("Name");
        appPreference.setDisplayEmail("Email");
        appPreference.setCreDate("DATE");
        appPreference.setCRId("ID");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }
}
