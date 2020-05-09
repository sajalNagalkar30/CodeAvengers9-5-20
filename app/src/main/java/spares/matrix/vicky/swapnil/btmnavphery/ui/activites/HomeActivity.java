package spares.matrix.vicky.swapnil.btmnavphery.ui.activites;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spares.matrix.vicky.swapnil.btmnavphery.R;
import spares.matrix.vicky.swapnil.btmnavphery.ui.adapters.MyExpandableListAdapter;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.BasketFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.CategoryFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.Customerser;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.Faqss;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.HomeFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.Notifications;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.OffersFragment;
import spares.matrix.vicky.swapnil.btmnavphery.ui.allfragments.MyOrder;
import spares.matrix.vicky.swapnil.btmnavphery.ui.categorystore.Fruits;
import spares.matrix.vicky.swapnil.btmnavphery.ui.model.GeneralFood;
import spares.matrix.vicky.swapnil.btmnavphery.ui.services.RetrofitClient;

import static spares.matrix.vicky.swapnil.btmnavphery.ui.activites.MainActivity.appPreference;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    BasketFragment basketFragment;
    HomeFragment home1;

    CategoryFragment category1;
    MyOrder myOrder;
    OffersFragment offersFragment;
    Customerser customerser;
    Faqss faqss;
    Notifications notifications;

    ExpandableListView expandableListView;
    MyExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Toolbar toolbar12;
    DrawerLayout drawerLayout;
    TextView namehel;
    Button add;
    RecyclerView recyclerViewVertical;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);

        toolbar12 = findViewById(R.id.toolbarfor_home);
        home1 = new HomeFragment();
        basketFragment = new BasketFragment();
        notifications = new Notifications();
        myOrder = new MyOrder();
        category1 = new CategoryFragment();
        faqss = new Faqss();
        offersFragment = new OffersFragment();
        customerser = new Customerser();
        toolbar =  findViewById(R.id.toolbarfor_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_sort);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        namehel = findViewById(R.id.name1);
        String Name = "Hello, " + MainActivity.appPreference.getDisplayName();
        namehel.setText(Name);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        //    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //  recyclerViewVertical.setLayoutManager(linearLayoutManager2);


        NavigationView navigationView = findViewById(R.id.navigationView);
        NavController navController = Navigation.findNavController(HomeActivity.this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);


        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(navView, navController);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_category, R.id.navigation_search, R.id.navigation_offers, R.id.navigation_basket)
                .setDrawerLayout(drawerLayout)
                .build();


        NavigationView navigationView1 = findViewById(R.id.navigationView);
        navigationView1.setNavigationItemSelectedListener(this);
        navigationView1.setNavigationItemSelectedListener(this);

        expandableListView = findViewById(R.id.lvExp);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                groupPosition=1;

                switch (childPosition)
                {
                    case 3:
                        logout();
                        break;
                }

                return false;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                switch (groupPosition) {
                    case 0:
                        change_Fragment(home1);
                        break;

                    case 2:
                        change_Fragment(category1);
                        break;

                    case 3:
                        change_Fragment(offersFragment);
                        break;

                    case 4:
                        change_Fragment(basketFragment);
                        break;

                    case 5:
                        change_Fragment(myOrder);
                        break;

                    case 6:
                        change_Fragment(notifications);
                        break;
                    case 7:
                        change_Fragment(customerser);
                        break;
                    case 8:
                        change_Fragment(faqss);
                        break;

                }
                if (groupPosition == 1) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else
                    drawerLayout.closeDrawer(GravityCompat.START);
                expandableListView.collapseGroup(groupPosition);
                return false;
            }
        });

        prepareListData();

        listAdapter = new MyExpandableListAdapter(this, listDataHeader, listDataChild);

        expandableListView.setAdapter(listAdapter);

    }


    private void prepareListData() {

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

// Adding Header Data
        listDataHeader.add("Home");
        listDataHeader.add("My Account");
        listDataHeader.add("Shop By Category");
        listDataHeader.add("Offers");
        listDataHeader.add("My Basket");
        listDataHeader.add("My Order");
        listDataHeader.add("Notification");
        listDataHeader.add("Customer Service ");
        listDataHeader.add("FAQ's");


// Adding child data
        List<String> Home = new ArrayList<String>();
        List<String> MyAccount = new ArrayList<String>();
        List<String> Shop = new ArrayList<String>();
        List<String> Offers1 = new ArrayList<String>();
        List<String> MyBasket1 = new ArrayList<String>();
        List<String> MyOrder1 = new ArrayList<String>();
        List<String> Notification1 = new ArrayList<String>();
        List<String> Customer1 = new ArrayList<String>();
        List<String> Faqs1 = new ArrayList<String>();
        MyAccount.add("My Profile");
        MyAccount.add("Change Password");
        MyAccount.add("Delivery Address");
        MyAccount.add("Logout");


        listDataChild.put(listDataHeader.get(0), Home);
        listDataChild.put(listDataHeader.get(1), MyAccount);// Header, Child data
        listDataChild.put(listDataHeader.get(2), Shop);
        listDataChild.put(listDataHeader.get(3), Offers1);
        listDataChild.put(listDataHeader.get(4), MyBasket1);
        listDataChild.put(listDataHeader.get(5), MyOrder1);
        listDataChild.put(listDataHeader.get(6), Notification1);
        listDataChild.put(listDataHeader.get(7), Customer1);
        listDataChild.put(listDataHeader.get(8), Faqs1);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        return false;
    }

    private void change_Fragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
        fragmentTransaction3.setCustomAnimations(R.anim.side_in_left, R.anim.side_out_left, R.anim.side_in_left, R.anim.side_out_left);
        fragmentTransaction3.addToBackStack(null);
        fragmentTransaction3.replace(R.id.nav_host_fragment, fragment).commit();

    }



    public void logout() {
        appPreference.setLoginStatus(false);
        appPreference.setDisplayName("Name");
        appPreference.setDisplayEmail("Email");
        appPreference.setCreDate("DATE");

       Intent intent=new Intent(getApplicationContext(),MainActivity.class);
       startActivity(intent);
    }
}

